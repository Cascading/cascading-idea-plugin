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

package cascading.intellij.plugin;

import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

public class DrivenProjectComponent implements ProjectComponent
  {
  @SuppressWarnings({"UnusedDeclaration"})
  public DrivenProjectComponent( Project project )
    {
    }

  @Override
  public void initComponent()
    {

    }

  @Override
  public void disposeComponent()
    {
    }

  @Override
  @NotNull
  public String getComponentName()
    {
    return "Driven Project Component";
    }

  @Override
  public void projectOpened()
    {
    }

  @Override
  public void projectClosed()
    {
    }

  }
