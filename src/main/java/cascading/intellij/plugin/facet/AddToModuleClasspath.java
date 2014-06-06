/*
 * Copyright (c) 2007-2014 Concurrent, Inc. All Rights Reserved.
 *
 * Project and contact information: http://www.cascading.org/
 *
 * This file is part of the Cascading project.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cascading.intellij.plugin.facet;

import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ModifiableRootModel;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.openapi.roots.OrderRootType;
import com.intellij.openapi.roots.libraries.Library;
import com.intellij.openapi.roots.libraries.LibraryTable;
import com.intellij.openapi.ui.Messages;
import com.intellij.util.PathUtil;

import java.awt.event.ActionEvent;
import java.util.List;

/**
 * Created by dhruv on 5/20/14.
 */
public class AddToModuleClasspath
  {

  private Project project;
  Module module = null;
  private List<String> libraryPaths;

  public AddToModuleClasspath( Project project, Module module, List<String> libraryPaths )
    {
    this.project = project;
    this.module = module;
    this.libraryPaths = libraryPaths;
    }

  public void addLibrary()
    {
    if( module != null && libraryPaths != null )
      {
      addLibraryToClasspath( module );
      }
    }

  private boolean isLibraryAlreadyInClasspath( LibraryTable libraryTable, String libURL )
    {
    Library[] existingLibraries = libraryTable.getLibraries();
    for( Library existingLibrary : existingLibraries )
      {
      String[] urls = existingLibrary.getRootProvider().getUrls( OrderRootType.CLASSES );
      for( String existingUrl : urls )
        {
        if( existingUrl.equalsIgnoreCase( libURL ) )
          {
          return true;
          }
        }
      }
    return false;
    }

  private void addLibraryToClasspath( final Module module )
    {

    Application application = ApplicationManager.getApplication();
    application.runWriteAction( new Runnable()
    {

    public void run()
      {
      try
        {
        final ModuleRootManager rootManager = ModuleRootManager.getInstance( module );
        final ModifiableRootModel rootModel = rootManager.getModifiableModel();
        for( String libraryPath : libraryPaths )
          {
          String escapedLibraryURL = cascading.intellij.plugin.util.PathUtil.escapeLibraryURL( libraryPath );
          final LibraryTable libraryTable = rootModel.getModuleLibraryTable();
          addLibrary( libraryTable, libraryPath, escapedLibraryURL );
          }
        if( rootModel.isWritable() )
          {
          rootModel.commit();
          }
        else
          {
          Messages.showErrorDialog( project, "The project configuration doesn't allow adding libraries to the classpath!", "Error" );
          }
        }
      catch( Throwable e )
        {
        Messages.showErrorDialog( project, "Error while adding library to classpath: " + e, "Error" );
        }
      }
    } );
    }

  private void addLibrary( LibraryTable libraryTable, String libraryPath, String escapedLibraryURL )
    {
    if( !isLibraryAlreadyInClasspath( libraryTable, escapedLibraryURL ) )
      {
      Library library = libraryTable.createLibrary();
      Library.ModifiableModel modifiableModel = library.getModifiableModel();
      modifiableModel.addRoot( escapedLibraryURL, OrderRootType.CLASSES );
      modifiableModel.commit();
      }
    else
      {
      Messages.showWarningDialog( project, "Library '" + libraryPath + "' already exists in classpath!", "Warning" );
      }
    }

  }
