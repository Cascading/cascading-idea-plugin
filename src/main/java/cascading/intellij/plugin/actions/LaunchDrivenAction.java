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

package cascading.intellij.plugin.actions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import cascading.intellij.plugin.util.DrivenAppIDFilePath;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.ide.BrowserUtil;
import com.intellij.openapi.diagnostic.Logger;

public class LaunchDrivenAction extends AnAction
  {

  private static final Logger LOG = Logger.getInstance( "#cascading.intellij.plugin.actions.LaunchDrivenAction" );

  public void actionPerformed( AnActionEvent e )
    {

    String tmpDirPath = DrivenAppIDFilePath.getOrCreateTempFilePath();
    LOG.info( "temp file path = " + tmpDirPath );
    File tmpDir = new File( tmpDirPath );
    BufferedReader br = null;
    try
      {
      br = new BufferedReader( new FileReader( tmpDir ) );
      }
    catch( FileNotFoundException exception )
      {
      exception.printStackTrace();
      }
    String appid = null;
    try
      {
      appid = br.readLine();
      }
    catch( IOException exception )
      {
      exception.printStackTrace();
      }
    BrowserUtil.browse( appid );
    }

  }


