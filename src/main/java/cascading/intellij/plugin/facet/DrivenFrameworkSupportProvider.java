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

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.intellij.facet.ui.FacetBasedFrameworkSupportProvider;
import com.intellij.facet.ui.libraries.LibraryInfo;
import com.intellij.framework.library.DownloadableLibraryService;
import com.intellij.framework.library.FrameworkSupportWithLibrary;
import com.intellij.ide.util.frameworkSupport.FrameworkSupportConfigurableBase;
import com.intellij.ide.util.frameworkSupport.FrameworkSupportModel;
import com.intellij.ide.util.frameworkSupport.FrameworkSupportProviderBase;
import com.intellij.ide.util.frameworkSupport.FrameworkVersion;
import com.intellij.openapi.application.PathManager;
import com.intellij.openapi.roots.ModifiableRootModel;
import com.intellij.openapi.roots.OrderRootType;
import com.intellij.openapi.roots.libraries.Library;
import com.intellij.openapi.roots.ui.configuration.libraries.CustomLibraryDescription;
import com.intellij.openapi.util.Pair;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileManager;
import com.intellij.openapi.vfs.impl.jar.JarFileSystemImpl;
import com.intellij.util.download.DownloadableFileDescription;
import com.intellij.util.download.DownloadableFileService;
import com.intellij.util.download.FileDownloader;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import cascading.intellij.plugin.library.DrivenLibraryType;

import static com.intellij.facet.ui.libraries.MavenLibraryUtil.createMavenJarInfo;

public class DrivenFrameworkSupportProvider extends FacetBasedFrameworkSupportProvider<DrivenFacet>
  {

  private static final String DOWNLOAD_URL = "http://eap.concurrentinc.com/driven/1.0/driven-plugin/driven-plugin-1.0-eap-57-io.jar";

  public DrivenFrameworkSupportProvider()
    {
    super( DrivenFacetType.INSTANCE );
    }

  @Override
  protected void setupConfiguration( DrivenFacet facet, ModifiableRootModel rootModel, FrameworkVersion version )
    {
    //
    }

  @Override
  public String getTitle()
    {
    return "Cascading";
    }

  @NotNull
  @Override
  public FrameworkSupportConfigurableBase createConfigurable( @NotNull FrameworkSupportModel model )
    {
    return new DrivenFrameworkSupportConfigurable( this, model, getVersions(), getVersionLabelText() );
    }


  /*
  @NotNull
  @Override
  public List<FrameworkVersion> getVersions()
    {
    List<FrameworkVersion> versions = new ArrayList<FrameworkVersion>();
    FrameworkVersion version = new FrameworkVersion( "2.5",
      "Driven", new LibraryInfo[]{
      createMavenJarInfo( "driven-plugin", "1.0-eap-67", "" )
    }, false
    );
    versions.add( version );
    return versions;
    }


  private void addDrivenJar(ModifiableRootModel modifiableRootModel) throws IOException
    {
    Library myLibrary = modifiableRootModel.getModuleLibraryTable().createLibrary("Driven JAR");
    Library.ModifiableModel libraryModel = myLibrary.getModifiableModel();

    File jarFile = drivenDownloader().get(0).first;
    System.out.println("Driven jar download path = " + jarFile.getAbsolutePath());
    VirtualFileManager virtualFileManager = VirtualFileManager.getInstance();
    VirtualFile virtualJarFile = virtualFileManager.findFileByUrl(jarFile.getAbsolutePath());
    VirtualFile jarRootForLocalFile = JarFileSystemImpl.getInstance().getJarRootForLocalFile(virtualJarFile);
    libraryModel.addRoot(jarRootForLocalFile, OrderRootType.CLASSES);
    libraryModel.commit();
    modifiableRootModel.commit();


    }

  private List<Pair<File, DownloadableFileDescription>> drivenDownloader() throws IOException {
  DownloadableFileService downloadableFileService = DownloadableFileService.getInstance();
  DownloadableFileDescription fileDescription = downloadableFileService.createFileDescription(DOWNLOAD_URL, "driven.jar");
  ArrayList<DownloadableFileDescription> downloadableFileDescriptions = new ArrayList<DownloadableFileDescription>();
  downloadableFileDescriptions.add(fileDescription);
  FileDownloader drivenDownloader = downloadableFileService.createDownloader(downloadableFileDescriptions, "driven");
  return drivenDownloader.download(new File( PathManager.getSystemPath(), "driven-libraries/"));

  }
       */

  private static class DrivenFrameworkSupportConfigurable extends FrameworkSupportConfigurableBase implements FrameworkSupportWithLibrary
    {
    private DrivenFrameworkSupportConfigurable( FrameworkSupportProviderBase frameworkSupportProvider,
                                                FrameworkSupportModel model,
                                                @NotNull List<FrameworkVersion> versions,
                                                @Nullable String versionLabelText )
      {
      super( frameworkSupportProvider, model, versions, versionLabelText );
      }

    @Override
    @NotNull
    public CustomLibraryDescription createLibraryDescription()
      {
      return DownloadableLibraryService.getInstance().createDescriptionForType( DrivenLibraryType.class );
      }

    @Override
    public boolean isLibraryOnly()
      {
      return false;
      }
    }
  }
