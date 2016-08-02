/**
 */
package model.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.january.geometry.GeometryFactory;
import org.eclipse.january.geometry.Triangle;
import org.eclipse.january.geometry.Vertex;
import org.junit.Test;

import model.MeshCache;
import model.ModelFactory;
import model.impl.MeshCacheImpl;

/**
 * <!-- begin-user-doc --> A test case for the model object ' <em><b>Mesh
 * Cache</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following operations are tested:
 * <ul>
 * <li>{@link model.MeshCache#getMesh(java.lang.String) <em>Get Mesh</em>}</li>
 * <li>{@link model.MeshCache#getMesh(org.eclipse.emf.common.util.EList) <em>Get
 * Mesh</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated NOT
 */
public class MeshCacheTest {

	/**
	 * The fixture for this Mesh Cache test case. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected MeshCache<?> fixture = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static void main(String[] args) {
	}

	/**
	 * Constructs a new Mesh Cache test case with the given name. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public MeshCacheTest() {
	}

	/**
	 * Sets the fixture for this Mesh Cache test case. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void setFixture(MeshCache<?> fixture) {
		this.fixture = fixture;
	}

	/**
	 * Returns the fixture for this Mesh Cache test case. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected MeshCache<?> getFixture() {
		return fixture;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	protected void setUp() throws Exception {
		setFixture(ModelFactory.eINSTANCE.createMeshCache());
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see junit.framework.TestCase#tearDown()
	 * @generated
	 */
	protected void tearDown() throws Exception {
		setFixture(null);
	}

	/**
	 * Tests the '{@link model.MeshCache#getMesh(java.lang.String) <em>Get
	 * Mesh</em>}' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see model.MeshCache#getMesh(java.lang.String)
	 * @generated NOT
	 */
	@Test
	public void checkGetMesh__String() {

		// The cache for testing
		TestCache cache = new TestCache();

		// The cache should return null when the requested type is not
		// recognized
		assertNull(cache.getMesh("missingType"));

		// Check that the default values are found
		assertTrue("value1".equals(cache.getMesh("test1")));
		assertTrue("value2".equals(cache.getMesh("test2")));

		// Check that the cache will return not just an identical value but the
		// same data object.
		assertTrue(cache.getMesh("test1") == cache.getMesh("test1"));
	}

	/**
	 * Tests the '
	 * {@link model.MeshCache#getMesh(org.eclipse.emf.common.util.EList) <em>Get
	 * Mesh</em>}' operation. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see model.MeshCache#getMesh(org.eclipse.emf.common.util.EList)
	 * @generated NOT
	 */
	@Test
	public void testGetMesh__EList() {

		// The cache for testing
		TestCache cache = new TestCache();

		// Create a list of triangles
		EList<Triangle> list1 = new BasicEList<Triangle>();
		Triangle tri1 = GeometryFactory.eINSTANCE.createTriangle();
		Vertex vertex1 = GeometryFactory.eINSTANCE.createVertex();
		tri1.getVertices().add(vertex1);
		Vertex vertex2 = GeometryFactory.eINSTANCE.createVertex();
		vertex2.setX(1);
		tri1.getVertices().add(vertex2);
		Vertex vertex3 = GeometryFactory.eINSTANCE.createVertex();
		vertex3.setY(1);
		tri1.getVertices().add(vertex3);
		Triangle tri2 = GeometryFactory.eINSTANCE.createTriangle();
		Vertex vertex4 = GeometryFactory.eINSTANCE.createVertex();
		tri2.getVertices().add(vertex4);
		Vertex vertex5 = GeometryFactory.eINSTANCE.createVertex();
		vertex5.setX(1);
		tri2.getVertices().add(vertex5);
		Vertex vertex6 = GeometryFactory.eINSTANCE.createVertex();
		tri2.getVertices().add(vertex6);
		vertex6.setY(-1);
		list1.add(tri1);
		list1.add(tri2);

		// Create a second, different list of triangles
		EList<Triangle> list2 = new BasicEList<Triangle>();
		Triangle tri3 = GeometryFactory.eINSTANCE.createTriangle();
		Vertex vertex7 = GeometryFactory.eINSTANCE.createVertex();
		tri3.getVertices().add(vertex7);
		Vertex vertex8 = GeometryFactory.eINSTANCE.createVertex();
		vertex8.setX(2);
		tri3.getVertices().add(vertex8);
		Vertex vertex9 = GeometryFactory.eINSTANCE.createVertex();
		vertex9.setY(2);
		tri3.getVertices().add(vertex9);
		Triangle tri4 = GeometryFactory.eINSTANCE.createTriangle();
		Vertex vertex10 = GeometryFactory.eINSTANCE.createVertex();
		tri4.getVertices().add(vertex10);
		Vertex vertex11 = GeometryFactory.eINSTANCE.createVertex();
		vertex11.setX(2);
		tri4.getVertices().add(vertex11);
		Vertex vertex12 = GeometryFactory.eINSTANCE.createVertex();
		tri4.getVertices().add(vertex12);
		vertex12.setY(-1);
		list2.add(tri3);
		list2.add(tri4);

		// The function should never return null
		assertTrue(cache.getMesh(list1) != null);

		// The cache should return different meshes for different inputs
		assertFalse(cache.getMesh(list1).equals(cache.getMesh(list2)));

		// The mesh should always return references to the same mesh for the
		// same input
		assertTrue(cache.getMesh(list2) == cache.getMesh(list2));

		// Check that the correct mesh is returned for two different lists with
		// identical values
		EList<Triangle> list3 = new BasicEList<Triangle>();
		list3.add(tri3);
		list3.add(tri4);
		assertTrue(cache.getMesh(list2) == cache.getMesh(list3));
	}

	/**
	 * A simple implementation of MeshCache that provides some default values
	 * for testing.
	 * 
	 * @author Robert Smith
	 *
	 * @generated NOT
	 */
	public class TestCache extends MeshCacheImpl<String> {

		/**
		 * The default constructor.
		 */
		public TestCache() {
			super();

			// Initialize the cache with some values
			typeCache.put("test1", "value1");
			typeCache.put("test2", "value2");
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see model.impl.MeshCacheImpl#createMesh(org.eclipse.emf.common.util.
		 * EList)
		 */
		@Override
		protected String createMesh(EList<Triangle> triangles) {

			// Start with an empty string
			String mesh = "";

			// Add the string representation of the normal and each vertex to
			// the output
			for (Triangle tri : triangles) {
				mesh += tri.getNormal();
				mesh += tri.getVertices().get(0);
				mesh += tri.getVertices().get(1);
				mesh += tri.getVertices().get(2);
			}

			return mesh;
		}
	}

} // MeshCacheTest
