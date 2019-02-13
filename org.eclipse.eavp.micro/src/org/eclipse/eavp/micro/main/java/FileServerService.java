/*******************************************************************************
 * Copyright (c) 2019 UT-Battelle, LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Robert Smith
 *******************************************************************************/
package org.eclipse.eavp.micro.main.java;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.PathSegment;

/**
 * An EAVP microservice which holds files for visualization in a central repository, so that other microservices can access them.
 * 
 * @author Robert Smith
 *
 */
@Path("/files")
public class FileServerService {
	
	@DELETE
	@Path("/delete/{path: .*}")
	public void delete(@PathParam("path") List<PathSegment> path) {
		 
		//Construc the path for the file and delete it
		String filePath = "/files";
	
		for (PathSegment segment : path) {
			filePath += "/" + segment.getPath();
		}
		
		new File(filePath).delete();
	}
	
	/**
	 * Retrieves a file from the server
	 * 
	 * @param path The path to the file, relative to the /files directory
	 * @return
	 */
	@GET
	@Path("/download/{path: .*}")
	@Produces(MediaType.APPLICATION_FORM_URLENCODED)
	public byte[] download(@PathParam("path") List<PathSegment> path) {
		
		//Construc the path for the file
		String filePath = "/files";
		
		for (PathSegment segment : path) {
			filePath += "/" + segment.getPath();
		}
		
		//Return the file's data if possible
		try {
			return Files.readAllBytes(Paths.get(filePath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}

	/**
	 * Uploads a file to the server, replacing any existing file at that location. Files are created in the /files directory
	 * 
	 * @param path The path, including file name, of the file being uploaded. /upload/foo/bar.txt will create a file at /files/foo/ named bar.txt.
	 * @param input The file's contents, to be specified via post
	 */
	@POST
	@Path("/upload/{path: .*}")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void upload(@PathParam("path") List<PathSegment> path, String input) {
		
		//Construc the path for the file
		String filePath = "/files";
		
		for (PathSegment segment : path) {
			filePath += "/" + segment.getPath();
		}
		
		try {
			
			//Make a stream for the file
			DataOutputStream stream = new DataOutputStream(new FileOutputStream(filePath));
			
			//Write the input into the file
			byte[] bytes = input.getBytes();
			for(int i = 0; i < bytes.length; i++) {
					stream.writeByte((int) bytes[i]);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
