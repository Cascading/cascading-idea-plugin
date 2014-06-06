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

import cascading.intellij.plugin.library.DrivenDownloadableLibraryServiceImpl;
import com.intellij.facet.FacetConfiguration;
import com.intellij.facet.impl.ui.FacetEditorsFactoryImpl;
import com.intellij.facet.ui.FacetEditorContext;
import com.intellij.facet.ui.FacetEditorTab;
import com.intellij.facet.ui.FacetValidatorsManager;
import com.intellij.framework.library.DownloadableLibraryService;
import com.intellij.util.messages.Topic;
import cascading.intellij.plugin.library.DrivenLibraryType;
import org.jdom.Element;

public class DrivenFacetConfiguration implements FacetConfiguration
  {
  private static final String RESOURCEURLS_TAG = "resourceUrls";
  private static final String RESOURCEURL_TAG = "resourceUrl";
  private static final String URL = "url";

  public static final Topic<Runnable> ADDITIONAL_PATHS_CHANGED = new Topic<Runnable>( "additional resource paths changed", Runnable.class );

  List<String> resourceUrls = new ArrayList<String>();

  @Override
  public FacetEditorTab[] createEditorTabs( FacetEditorContext editorContext, FacetValidatorsManager validatorsManager )
    {

    /*
    validatorsManager.registerValidator( FacetEditorsFactoryImpl.getInstanceImpl().createLibraryValidator(
      DownloadableLibraryService.getInstance().createDescriptionForType( DrivenLibraryType.class ),
      editorContext,
      validatorsManager,
      "Driven Plugin"
    ) );
    return new FacetEditorTab[]{new DrivenFacetEditorTab( editorContext )};

    */

    // todo: This is a solution for: How to download the library to a location other than "${project.root}/lib"?
    //  this is called from FacetEditorImpl's constructor:
    //  myEditorTabs = configuration.createEditorTabs(context, myErrorPanel.getValidatorsManager())
    //  here we need to create a custom FacetErrorPanel containing a custom FacetConfigurationQuickFix
    //  copy paste the FacetErrorPanel to create DrivenFacetErrorPanel, create a new DrivenFacetValidatorsManager
    //  and use that to bypass the normal Library Download setting control flow.

    validatorsManager.registerValidator( FacetEditorsFactoryImpl.getInstanceImpl().createLibraryValidator(
      new DrivenDownloadableLibraryServiceImpl().createDescriptionForType( DrivenLibraryType.class ),
      editorContext,
      validatorsManager,
      "Driven Plugin"
    ) );
    return new FacetEditorTab[]{new DrivenFacetEditorTab( editorContext )};
    }

  @Override
  @Deprecated
  public void readExternal( Element element )
    {
    Element resourceUrlsElement = element.getChild( RESOURCEURLS_TAG );
    if( resourceUrlsElement != null )
      {
      List resourceUrlsChildren = resourceUrlsElement.getChildren( RESOURCEURL_TAG );
      if( resourceUrlsChildren != null )
        {
        for( Object child : resourceUrlsChildren )
          {
          if( child instanceof Element )
            {
            resourceUrls.add( ( (Element) child ).getAttributeValue( URL ) );
            }
          }
        }
      }
    }

  @Override
  @Deprecated
  public void writeExternal( Element element )
    {
    Element resourceUrlsElement = new Element( RESOURCEURLS_TAG );
    element.addContent( resourceUrlsElement );
    for( String resourceUrl : resourceUrls )
      {
      Element child = new Element( RESOURCEURL_TAG );
      child.setAttribute( URL, resourceUrl );
      resourceUrlsElement.addContent( child );
      }
    }
  }
