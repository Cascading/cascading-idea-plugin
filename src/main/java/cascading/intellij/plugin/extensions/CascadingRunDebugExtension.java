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

package cascading.intellij.plugin.extensions;

import cascading.intellij.plugin.util.DrivenAppIDFilePath;
import com.intellij.execution.ExecutionException;
import com.intellij.execution.RunConfigurationExtension;
import com.intellij.execution.configurations.JavaParameters;
import com.intellij.execution.configurations.RunConfigurationBase;
import com.intellij.execution.configurations.RunnerSettings;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.util.InvalidDataException;
import com.intellij.openapi.util.WriteExternalException;
import org.jdom.Element;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CascadingRunDebugExtension extends RunConfigurationExtension
  {

  @Override
  public <T extends RunConfigurationBase> void updateJavaParameters( T configuration, JavaParameters params, RunnerSettings runnerSettings ) throws ExecutionException
    {

    params.getVMParametersList().addParametersString( "-Ddriven.appid.file=" + DrivenAppIDFilePath.getOrCreateTempFilePath() );

    }

  @Override
  protected void readExternal( @NotNull RunConfigurationBase runConfiguration, @NotNull Element element ) throws InvalidDataException
    {

    }

  @Override
  protected void writeExternal( @NotNull RunConfigurationBase runConfiguration, @NotNull Element element ) throws WriteExternalException
    {

    }

  @Nullable
  @Override
  protected String getEditorTitle()
    {
    return null;
    }

  @Override
  protected boolean isApplicableFor( @NotNull RunConfigurationBase configuration )
    {
    return false;
    }

  @Override
  protected SettingsEditor createEditor( @NotNull RunConfigurationBase configuration )
    {
    return null;
    }
  }
