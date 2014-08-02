
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
import java.io.IOException;

import com.intellij.openapi.diagnostic.Logger;

public final class DrivenAppIDFilePath
  {
  private static final Logger LOG = Logger.getInstance( "#cascading.intellij.plugin.util.DrivenAppIDFilePath" );
  private static String drivenFilePath = null;

  private DrivenAppIDFilePath()
    {
    }

  public static String getOrCreateTempFilePath()
    {
    if( drivenFilePath != null )
      return drivenFilePath;

    File tmpFile;

    try
      {
      tmpFile = File.createTempFile( "driven", ".appid" );
      drivenFilePath = tmpFile.getAbsolutePath();

      LOG.info( "Created temporary file for writing Driven AppID at " + drivenFilePath );
      }
    catch( IOException exception )
      {
      LOG.warn( "unable to create temp path", exception );
      }

    return drivenFilePath;
    }
  }
