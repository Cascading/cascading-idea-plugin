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

import java.util.ArrayList;
import java.util.List;

import cascading.intellij.plugin.util.DrivenAppIDFilePath;
import com.intellij.facet.Facet;
import com.intellij.facet.FacetManager;
import com.intellij.facet.FacetType;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleUtil;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.pointers.VirtualFilePointer;
import com.intellij.openapi.vfs.pointers.VirtualFilePointerManager;
import com.intellij.psi.JavaPsiFacade;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiUtil;
import cascading.intellij.plugin.search.DrivenSearchScope;
import cascading.intellij.plugin.util.DrivenFileUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DrivenFacet extends Facet<DrivenFacetConfiguration>
  {
  private List<VirtualFilePointer> resourcePaths;

  public DrivenFacet( @NotNull FacetType facetType, @NotNull Module module, String name, @NotNull DrivenFacetConfiguration configuration, Facet underlyingFacet )
    {
    super( facetType, module, name, configuration, underlyingFacet );
    DrivenAppIDFilePath.getOrCreateTempFilePath();
    }

  @NotNull
  public List<VirtualFilePointer> getResourcePaths()
    {
    if( resourcePaths == null )
      {
      resourcePaths = new ArrayList<VirtualFilePointer>();
      for( String resourceUrl : getConfiguration().resourceUrls )
        {
        resourcePaths.add( VirtualFilePointerManager.getInstance().create( resourceUrl, getModule(), null ) );
        }
      }
    return resourcePaths;
    }

  public void setResourcePaths( @NotNull List<VirtualFilePointer> resourcePaths )
    {
    this.resourcePaths = resourcePaths;
    DrivenFacetConfiguration configuration = getConfiguration();
    configuration.resourceUrls.clear();
    for( VirtualFilePointer virtualFilePointer : resourcePaths )
      {
      configuration.resourceUrls.add( FileUtil.toSystemIndependentName( virtualFilePointer.getUrl() ) );
      }
    }

  @Nullable
  public static DrivenFacet getInstance( @NotNull final Module module )
    {
    return FacetManager.getInstance( module ).getFacetByType( DrivenFacetType.ID );
    }

  /**
   * Returns true if the passed Module contains Cascading class.
   *
   * @param module Module
   * @return boolean
   */
  public static boolean isLibraryPresent( @Nullable Module module )
    {
    if( module == null )
      {
      return false;
      }
    JavaPsiFacade psiFacade = JavaPsiFacade.getInstance( module.getProject() );
    return psiFacade.findClass( "cascading.flow.Flow", DrivenSearchScope.classInModuleWithDependenciesAndLibraries( module ) ) != null;
    }

  /**
   * @param element
   * @return true if element has DrivenFacet or is from library
   */
  public static boolean hasFacetOrIsFromLibrary( @Nullable PsiElement element )
    {
    if( element != null )
      {
      VirtualFile vf = PsiUtil.getVirtualFile( element );
      if( vf != null )
        {
        Project project = element.getProject();
        Module module = ModuleUtil.findModuleForFile( vf, project );
        if( module != null )
          {
          return getInstance( module ) != null;
          }
        return DrivenFileUtil.isInLibrary( vf, project );
        }
      }
    return false;
    }
  }

