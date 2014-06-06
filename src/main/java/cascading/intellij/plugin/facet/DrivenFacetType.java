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

import javax.swing.*;

import com.intellij.facet.Facet;
import com.intellij.facet.FacetType;
import com.intellij.facet.FacetTypeId;
import com.intellij.facet.FacetTypeRegistry;
import com.intellij.framework.detection.FacetBasedFrameworkDetector;
import com.intellij.framework.detection.FileContentPattern;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.fileTypes.StdFileTypes;
import com.intellij.openapi.module.JavaModuleType;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleType;
import com.intellij.patterns.ElementPattern;
import com.intellij.patterns.PatternCondition;
import com.intellij.psi.JavaPsiFacade;
import com.intellij.util.ProcessingContext;
import com.intellij.util.indexing.FileContent;
import cascading.intellij.plugin.Constants;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DrivenFacetType extends FacetType<DrivenFacet, DrivenFacetConfiguration>
  {

  private static final String STRING_ID = "cascading";
  private static final String PRESENTABLE_NAME = "Cascading";
  public static final FacetTypeId<DrivenFacet> ID = new FacetTypeId<DrivenFacet>( STRING_ID );
  public static final DrivenFacetType INSTANCE = new DrivenFacetType();

  public DrivenFacetType()
    {
    super( ID, STRING_ID, PRESENTABLE_NAME );
    }

  @Override
  public DrivenFacetConfiguration createDefaultConfiguration()
    {
    return new DrivenFacetConfiguration();
    }

  @Override
  public DrivenFacet createFacet( @NotNull Module module, String name, @NotNull DrivenFacetConfiguration configuration, @Nullable Facet underlyingFacet )
    {
    return new DrivenFacet( this, module, name, configuration, underlyingFacet );
    }

  @Override
  public boolean isSuitableModuleType( ModuleType moduleType )
    {
    return ( moduleType instanceof JavaModuleType );
    }

  @Override
  public boolean isOnlyOneFacetAllowed()
    {
    return true;
    }

  @Override
  public Icon getIcon()
    {
    return Constants.CASCADING_ICON;
    }

  public static class DrivenFacetDetector extends FacetBasedFrameworkDetector<DrivenFacet, DrivenFacetConfiguration>
    {
    public DrivenFacetDetector()
      {
      super( STRING_ID );
      }

    @Override
    public FacetType<DrivenFacet, DrivenFacetConfiguration> getFacetType()
      {
      //noinspection unchecked
      return FacetTypeRegistry.getInstance().findFacetType( STRING_ID );
      }

    @NotNull
    @Override
    public FileType getFileType()
      {
      return StdFileTypes.JAVA;
      }

    @NotNull
    @Override
    public ElementPattern<FileContent> createSuitableFilePattern()
      {
      return FileContentPattern.fileContent().with( new PatternCondition<FileContent>( "cascading" )
      {
      @Override
      public boolean accepts( @NotNull final FileContent fileContent, final ProcessingContext context )
        {
        return fileContent.getContentAsText().toString().contains( "cascading.flow.Flow" );
        }
      } );
      }
    }
  }
