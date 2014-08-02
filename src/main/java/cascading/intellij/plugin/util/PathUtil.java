
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

package cascading.intellij.plugin.util;

import java.io.File;

import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.vfs.VfsUtil;

public class PathUtil
  {
  public static String escapeLibraryURL( String libraryPath )
    {
    String url = VfsUtil.pathToUrl( FileUtil.toSystemIndependentName( libraryPath ) );
    return url.replaceAll( "file:", "jar:" ) + "!/";
    }

  public static String getProjectRootFolder( Project project )
    {
    ModuleManager moduleManager = ModuleManager.getInstance( project );
    Module[] modules = moduleManager.getModules();

    if( modules.length > 0 )
      return new File( modules[ 0 ].getModuleFilePath() ).getParent();
    else
      return new File( "." ).getParent();
    }
  }
