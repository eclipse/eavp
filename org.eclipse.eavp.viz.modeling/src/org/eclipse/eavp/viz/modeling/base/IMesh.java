/*******************************************************************************
 * Copyright (c) 2016 UT-Battelle, LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Robert Smith
 *******************************************************************************/
package org.eclipse.eavp.viz.modeling.base;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import org.eclipse.eavp.viz.datastructures.VizObject.IManagedUpdateable;
import org.eclipse.eavp.viz.datastructures.VizObject.IManagedUpdateableListener;
import org.eclipse.eavp.viz.modeling.properties.IMeshCategory;
import org.eclipse.eavp.viz.modeling.properties.IMeshProperty;
import org.eclipse.eavp.viz.modeling.properties.MeshType;

/**
 * A component of the model. All models are built from collections of IMeshes in
 * a hierarchical structure. An IMesh represents some concrete entity which can
 * be displayed inside the graphics engine.
 * 
 * @author Robert Smith
 */
public interface IMesh extends IManagedUpdateable, IManagedUpdateableListener {

	/**
	 * Getter method for type.
	 * 
	 * @return The mesh's type
	 */
	MeshType getType();

	/**
	 * Setter method for the mesh's type
	 */
	void setType(MeshType type);

	/**
	 * Returns a list of all related entities.
	 * 
	 * @return All related entities.
	 */
	ArrayList<IController> getAllEntities();

	/**
	 * Return all of the part's children entities of a given category.
	 * 
	 * @param category
	 *            The category of entities to return
	 * @return All the entities in the map for the specified category.
	 */
	ArrayList<IController> getEntitiesFromCategory(IMeshCategory category);

	/**
	 * Return all of the part's children entities of a given category cast to a
	 * list of the specified return type.
	 * 
	 * @param category
	 *            The category of entities to return
	 * @param returnType
	 *            The class to which members of the category belong.
	 * @return All the entities in the map for the specified category, cast to
	 *         the given type.
	 */
	<T extends IController> ArrayList<T> getEntitiesFromCategory(
			IMeshCategory category, Class<T> returnType);

	/**
	 * Return the value of the given property
	 * 
	 * @property The property to return
	 */
	String getProperty(IMeshProperty property);

	/**
	 * Set the given property, creating it in the map if it is not already
	 * present.
	 * 
	 * @generated NOT
	 * 
	 * @property The property to set
	 * @value The property's new value
	 */
	void setProperty(IMeshProperty property, String value);

	/**
	 * Get the entire properties map for the mesh.
	 * 
	 * @return The map of all properties.
	 */
	Map<IMeshProperty, String> getPropertyMap();

	/**
	 * Add a new entity to the part. A convenience method which allows for the
	 * specification of a default behavior for new entities when no category is
	 * specified.
	 * 
	 * @generated NOT
	 * 
	 * @newEntity The child entity to add to the part.
	 */
	void addEntity(IController newEntity);

	/**
	 * Removes the given entity from the part's children
	 *
	 * @generated NOT
	 * 
	 * @entity The entity to be removed
	 */
	void removeEntity(IController entity);

	/**
	 * Adds a new child entity under the given category.
	 *
	 * @generated NOT
	 * 
	 * @param newEntity
	 *            The new child entity to be added
	 * @param category
	 *            The new entity's category
	 */
	void addEntityToCategory(IController newEntity, IMeshCategory category);

	/**
	 * Deep copies the contents of another AbstractMeshComponent into this one.
	 * This does not copy the reference to any parent IController, as an
	 * IController should have exactly one AbstractMesh as a model.
	 * 
	 * @param otherObject
	 *            The object which will be copied into this.
	 */
	void copy(IMesh otherObject);

	/**
	 * Getter method for the controller.
	 * 
	 * @return The IController which manages this component
	 */
	IController getController();

	/**
	 * Setter method for the controller.
	 * 
	 * @param controller
	 *            The IController which manages this component
	 */
	void setController(IController controller);

	/**
	 * Get the set of all categories in the mesh's entities map.
	 * 
	 * @return The set of all categories in the mesh's entities map.
	 */
	Set<IMeshCategory> getEntityCategories();

	/**
	 * Get the entities map in the form of an array of map entries. This method
	 * is intended for use by the JAXB marshaler.
	 * 
	 * @return An array of map entries, each with an IMeshCategory and all
	 *         IControllers in that category.
	 */
	public Map<IMeshCategory, ArrayList<IController>> getEntities();

	/**
	 * Get the properties map in the form of an array of map entries. This
	 * method is inteded for use by the JAXB marshaler. Clients should call
	 * getProperyMap() instead.
	 * 
	 * @return An array of map entries, each with an IMeshProperty and its
	 *         associated setting.
	 */
	public Map<IMeshProperty, String> getProperties();

	/**
	 * Set the mesh's map of entities to be equal to the provided map. This will
	 * be equivalent to clearing the map with removeEntity() calls and adding
	 * each individual member of newEntities as if by invoking
	 * addEntityToCategory().
	 * 
	 * @param entities
	 */
	void setEntities(Map<IMeshCategory, ArrayList<IController>> entities);

	/**
	 * Set the mesh's properties according to the given map.
	 * 
	 * @param properties
	 *            The new map of properties for the mesh.
	 */
	void setProperties(Map<IMeshProperty, String> properties);

}