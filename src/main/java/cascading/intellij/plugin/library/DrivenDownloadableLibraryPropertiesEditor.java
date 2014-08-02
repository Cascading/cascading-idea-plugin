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

package cascading.intellij.plugin.library;

import java.util.List;

import com.intellij.facet.impl.ui.libraries.DownloadingOptionsDialog;
import com.intellij.facet.impl.ui.libraries.LibraryDownloadSettings;
import com.intellij.framework.library.DownloadableLibraryDescription;
import com.intellij.framework.library.DownloadableLibraryType;
import com.intellij.framework.library.FrameworkLibraryVersion;
import com.intellij.framework.library.LibraryVersionProperties;
import com.intellij.framework.library.impl.DownloadableLibraryPropertiesEditor;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.application.ModalityState;
import com.intellij.openapi.application.PathManager;
import com.intellij.openapi.roots.libraries.ui.LibraryEditorComponent;
import com.intellij.openapi.roots.ui.configuration.libraryEditor.LibraryEditorBase;
import com.intellij.openapi.roots.ui.configuration.libraryEditor.NewLibraryEditor;
import com.intellij.openapi.roots.ui.configuration.projectRoot.LibrariesContainer;
import com.intellij.util.download.DownloadableFileSetVersions;
import org.jetbrains.annotations.NotNull;

/**
 *
 */
public class DrivenDownloadableLibraryPropertiesEditor extends DownloadableLibraryPropertiesEditor
  {

  private final DownloadableLibraryDescription myDescription;
  private final DownloadableLibraryType myLibraryType;
  private String myCurrentVersionString;

  public DrivenDownloadableLibraryPropertiesEditor( DownloadableLibraryDescription description, LibraryEditorComponent<LibraryVersionProperties> editorComponent, DownloadableLibraryType libraryType )
    {
    super( description, editorComponent, libraryType );
    myDescription = description;
    myLibraryType = libraryType;
    myCurrentVersionString = myEditorComponent.getProperties().getVersionString();
    }

  @Override
  protected void edit()
    {

    System.out.println( "\n\n\n\n Inside the edit method \n\n\n" );
    final ModalityState current = ModalityState.current();
    myDescription.fetchVersions( new DownloadableFileSetVersions.FileSetVersionsCallback<FrameworkLibraryVersion>()
    {
    @Override
    public void onSuccess( @NotNull final List<? extends FrameworkLibraryVersion> versions )
      {
      ApplicationManager.getApplication().invokeLater( new Runnable()
      {
      @Override
      public void run()
        {
        String pathForDownloaded = PathManager.getLibPath();
        /*
        final VirtualFile existingRootDirectory = myEditorComponent.getExistingRootDirectory();
        if( existingRootDirectory != null )
          {
          pathForDownloaded = existingRootDirectory.getPath();
          }
        else
          {
          final VirtualFile baseDir = myEditorComponent.getBaseDirectory();
          if( baseDir != null )
            {
            pathForDownloaded = baseDir.getPath() + "/lib";
            }
          }
          */
        final LibraryDownloadSettings initialSettings = new LibraryDownloadSettings( getCurrentVersion( versions ), myLibraryType,
          LibrariesContainer.LibraryLevel.GLOBAL,
          pathForDownloaded );
        final LibraryDownloadSettings settings = DownloadingOptionsDialog.showDialog( getMainPanel(), initialSettings, versions, false );
        if( settings != null )
          {
          final NewLibraryEditor editor = settings.download( getMainPanel(), null );
          if( editor != null )
            {
            final LibraryEditorBase target = (LibraryEditorBase) myEditorComponent.getLibraryEditor();
            target.removeAllRoots();
            myEditorComponent.renameLibrary( editor.getName() );
            target.setType( myLibraryType );
            editor.applyTo( target );
            myEditorComponent.updateRootsTree();
            myCurrentVersionString = settings.getVersion().getVersionString();
            setModified();
            }
          }
        }
      }, current );
      }
    } );
    }

  private FrameworkLibraryVersion getCurrentVersion( List<? extends FrameworkLibraryVersion> versions )
    {
    for( FrameworkLibraryVersion version : versions )
      {
      if( version.getVersionString().equals( myCurrentVersionString ) )
        return version;
      }

    return versions.get( 0 );
    }
  }
