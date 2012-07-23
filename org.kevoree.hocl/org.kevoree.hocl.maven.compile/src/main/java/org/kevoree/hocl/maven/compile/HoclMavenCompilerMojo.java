package org.kevoree.hocl.maven.compile;

import fr.inria.hocl.core.hoclc2j.ParseException;
import fr.inria.hocl.core.hoclc2j.Program;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.project.MavenProject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;

/**
 * User: Erwan Daubert - erwan.daubert@gmail.com
 * Date: 15/03/12
 * Time: 12:58
 *
 * @author Erwan Daubert
 * @version 1.0
 * @goal generate
 * @phase generate-sources
 * @requiresDependencyResolution compile
 */
public class HoclMavenCompilerMojo extends AbstractMojo {

	/**
	 * @parameter default-value="${project.basedir}/src/main/hocl"
	 */
	private File sourceHoclDirectory;

	/**
	 * The directory root under which processor-generated source files will be placed; files are placed in
	 * subdirectories based on package namespace. This is equivalent to the <code>-s</code> argument for apt.
	 *
	 * @parameter default-value="${project.build.directory}/generated-sources/java"
	 */
	private File sourceOutputDirectory;
	/**
	 * The maven project.
	 *
	 * @parameter expression="${project}"
	 * @required
	 * @readonly
	 */
	private MavenProject project;

	public void execute () throws MojoExecutionException {

		executeOnDirectory(sourceHoclDirectory);

		project.addCompileSourceRoot(sourceOutputDirectory.getAbsolutePath());
	}

	private void executeOnDirectory (File dir) throws MojoExecutionException {
		File outputDir = new File(dir.getAbsolutePath().replace(sourceHoclDirectory.getAbsolutePath(), sourceOutputDirectory.getAbsolutePath()));
		// build packages
		if (!outputDir.exists() && !outputDir.mkdirs()) {
			throw new MojoExecutionException("Unable to build target packages " + outputDir.getAbsolutePath());
		}

		// list .hocl file into dir
		for (File f : dir.listFiles(new HoclFileNameFilter())) {
			if (f.isDirectory()) {
				executeOnDirectory(f);
			} else {
				try {
					String className = upperFirstLetter(fileBasename(f.getName())) + "_gen";
					HoclJavaGenerator generator = new HoclJavaGenerator();
					Program program = generator.parse(f);
					generator.optimize(program);
					generator.generate(program, className, outputDir.getAbsolutePath());
				} catch (FileNotFoundException e) {
					throw new MojoExecutionException("Unable to use the source file: " + f.getAbsolutePath(), e);
				} catch (ParseException e) {
					throw new MojoExecutionException(
							"Encountered errors during parse.\n" + "Line " + e.currentToken.beginLine + ", " + "Column " + (e.currentToken.beginColumn - 1) + ": " + e.getMessage(), e);
				} catch (IOException e) {
					throw new MojoExecutionException("Unable to generate code for " + f.getAbsolutePath(), e);
				}
			}
		}
	}

	private String fileBasename (String filename) {
		try {
			return filename.substring(0, filename.indexOf('.'));
		} catch (Exception e) {
			return filename;
		}
	}


	public static String upperFirstLetter (String str) {
		if (str.length() > 0) {
			return Character.toUpperCase(str.charAt(0)) + str.substring(1);
		} else {
			return str;
		}
	}


	private class HoclFileNameFilter implements FilenameFilter {

		public boolean accept (File dir, String name) {
			return name.toLowerCase().endsWith(".hocl") || new File(dir.getAbsolutePath() + File.separator + name).isDirectory();
		}
	}

	/*public boolean copyFile (InputStream sourceFile, File destFile) throws IOException {
		if (!destFile.exists() && destFile.createNewFile()) {

			ReadableByteChannel source = null;
			FileChannel destination = null;
			try {
				source = Channels.newChannel(sourceFile);
				destination = new FileOutputStream(destFile).getChannel();
				destination.transferFrom(source, 0, sourceFile.available());
			} finally {
				if (source != null) {
					source.close();
				}
				if (destination != null) {
					destination.close();
				}
			}
			return true;
		}
		return false;
	}*/
}
