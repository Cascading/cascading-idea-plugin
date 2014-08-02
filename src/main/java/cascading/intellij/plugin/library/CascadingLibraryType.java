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

import cascading.intellij.plugin.Constants;
import com.intellij.framework.library.DownloadableLibraryDescription;
import com.intellij.framework.library.DownloadableLibraryType;
import com.intellij.framework.library.LibraryVersionProperties;
import com.intellij.openapi.roots.libraries.ui.LibraryEditorComponent;
import com.intellij.openapi.roots.libraries.ui.LibraryPropertiesEditor;
import org.jetbrains.annotations.NotNull;

public class CascadingLibraryType extends DownloadableLibraryType
  {
  private static final String[] DETECTION_CLASS_NAMES = new String[]{"cascading.flow.Flow"};
  private static final String GROUPID = "cascading";
  private static final URL LOCAL_URL = getUrl( "cascading" );

  private final DownloadableLibraryDescription myLibraryDescription;

  public CascadingLibraryType()
    {
    super( "Cascading", "cascading", "cascading", Constants.CASCADING_ICON, getUrl( "cascading" ) );

    //PicoContainer picoContainer = Thread.currentThread().getContextClassLoader().loadClass( "org.picocontainer.PicoContainer" ).getMethod(  )
    myLibraryDescription = new DrivenDownloadableLibraryServiceImpl().createLibraryDescription( "cascading", LOCAL_URL );
    }

  @Override
  protected String[] getDetectionClassNames()
    {
    return DETECTION_CLASS_NAMES;
    }

  private static URL getUrl( @NotNull String lib )
    {
    return CascadingLibraryType.class.getResource( "/" + lib + ".xml" );
    }

  @Override
  public LibraryPropertiesEditor createPropertiesEditor( @NotNull LibraryEditorComponent<LibraryVersionProperties> editorComponent )
    {
    LibraryPropertiesEditor drivenDownloadableLibraryPropertiesEditor = new DrivenDownloadableLibraryServiceImpl().createDownloadableLibraryEditor( myLibraryDescription, editorComponent, this );

    return drivenDownloadableLibraryPropertiesEditor;
    }

  @Override
  public String getDescription( @NotNull LibraryVersionProperties properties )
    {
    return super.getDescription( properties );
    }

  @NotNull
  @Override
  public DownloadableLibraryDescription getLibraryDescription()
    {
    return myLibraryDescription;
    }

  }
