/**
 */
package de.iils.dc43.graph;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Node</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.iils.dc43.graph.Node#getOut <em>Out</em>}</li>
 *   <li>{@link de.iils.dc43.graph.Node#getIn <em>In</em>}</li>
 *   <li>{@link de.iils.dc43.graph.Node#getImage <em>Image</em>}</li>
 * </ul>
 *
 * @see de.iils.dc43.graph.GraphPackage#getNode()
 * @model
 * @generated
 */
public interface Node extends Position, GraphObject {
	/**
	 * Returns the value of the '<em><b>Out</b></em>' reference list.
	 * The list contents are of type {@link de.iils.dc43.graph.Edge}.
	 * It is bidirectional and its opposite is '{@link de.iils.dc43.graph.Edge#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Out</em>' reference list.
	 * @see de.iils.dc43.graph.GraphPackage#getNode_Out()
	 * @see de.iils.dc43.graph.Edge#getSource
	 * @model opposite="source"
	 * @generated
	 */
	EList<Edge> getOut();

	/**
	 * Returns the value of the '<em><b>In</b></em>' reference list.
	 * The list contents are of type {@link de.iils.dc43.graph.Edge}.
	 * It is bidirectional and its opposite is '{@link de.iils.dc43.graph.Edge#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>In</em>' reference list.
	 * @see de.iils.dc43.graph.GraphPackage#getNode_In()
	 * @see de.iils.dc43.graph.Edge#getTarget
	 * @model opposite="target"
	 * @generated
	 */
	EList<Edge> getIn();

	/**
	 * Returns the value of the '<em><b>Image</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Image</em>' attribute.
	 * @see #setImage(String)
	 * @see de.iils.dc43.graph.GraphPackage#getNode_Image()
	 * @model
	 * @generated
	 */
	String getImage();

	/**
	 * Sets the value of the '{@link de.iils.dc43.graph.Node#getImage <em>Image</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Image</em>' attribute.
	 * @see #getImage()
	 * @generated
	 */
	void setImage(String value);

} // Node
