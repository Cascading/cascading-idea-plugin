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

import java.net.URL;

import com.intellij.framework.library.DownloadableLibraryDescription;
import com.intellij.framework.library.DownloadableLibraryService;
import com.intellij.framework.library.DownloadableLibraryType;
import com.intellij.framework.library.LibraryVersionProperties;
import com.intellij.framework.library.impl.DownloadableLibraryServiceImpl;
import com.intellij.openapi.roots.libraries.ui.LibraryEditorComponent;
import com.intellij.openapi.roots.libraries.ui.LibraryPropertiesEditor;
import com.intellij.openapi.roots.ui.configuration.libraries.CustomLibraryDescription;
import com.intellij.util.download.impl.DownloadableFileServiceImpl;
import org.jetbrains.annotations.NotNull;

/**
 *
 */
public class DrivenDownloadableLibraryServiceImpl extends DownloadableLibraryServiceImpl
  {

  @NotNull
  @Override
  public LibraryPropertiesEditor createDownloadableLibraryEditor( @NotNull DownloadableLibraryDescription description,
                                                                  @NotNull LibraryEditorComponent<LibraryVersionProperties> editorComponent,
                                                                  @NotNull DownloadableLibraryType libraryType )
    {

    System.out.println( "inside createDownloadableLibraryEditor" );
    return new DrivenDownloadableLibraryPropertiesEditor( description, editorComponent, libraryType );
    }

  @NotNull
  @Override
  public CustomLibraryDescription createDescriptionForType( Class<? extends DownloadableLibraryType> typeClass )
    {
    return super.createDescriptionForType( typeClass );
    }

  @NotNull
  @Override
  public DownloadableLibraryDescription createLibraryDescription( @NotNull String groupId, @NotNull URL... localUrls )
    {
    System.out.println( "inside createLibraryDescription" );
    return super.createLibraryDescription( groupId, localUrls );
    }
  }
