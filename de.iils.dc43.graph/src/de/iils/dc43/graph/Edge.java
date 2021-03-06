/**
 */
package de.iils.dc43.graph;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Edge</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.iils.dc43.graph.Edge#getSource <em>Source</em>}</li>
 *   <li>{@link de.iils.dc43.graph.Edge#getTarget <em>Target</em>}</li>
 * </ul>
 *
 * @see de.iils.dc43.graph.GraphPackage#getEdge()
 * @model
 * @generated
 */
public interface Edge extends GraphObject {
	/**
	 * Returns the value of the '<em><b>Source</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link de.iils.dc43.graph.Node#getOut <em>Out</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Source</em>' reference.
	 * @see #setSource(Node)
	 * @see de.iils.dc43.graph.GraphPackage#getEdge_Source()
	 * @see de.iils.dc43.graph.Node#getOut
	 * @model opposite="out" required="true"
	 * @generated
	 */
	Node getSource();

	/**
	 * Sets the value of the '{@link de.iils.dc43.graph.Edge#getSource <em>Source</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source</em>' reference.
	 * @see #getSource()
	 * @generated
	 */
	void setSource(Node value);

	/**
	 * Returns the value of the '<em><b>Target</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link de.iils.dc43.graph.Node#getIn <em>In</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target</em>' reference.
	 * @see #setTarget(Node)
	 * @see de.iils.dc43.graph.GraphPackage#getEdge_Target()
	 * @see de.iils.dc43.graph.Node#getIn
	 * @model opposite="in" required="true"
	 * @generated
	 */
	Node getTarget();

	/**
	 * Sets the value of the '{@link de.iils.dc43.graph.Edge#getTarget <em>Target</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target</em>' reference.
	 * @see #getTarget()
	 * @generated
	 */
	void setTarget(Node value);

} // Edge
