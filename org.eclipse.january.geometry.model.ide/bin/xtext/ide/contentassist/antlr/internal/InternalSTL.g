/*******************************************************************************
 * Copyright (c) 2016 UT-Battelle, LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Initial API and implementation and/or initial documentation - 
 *   Kasper Gammeltoft
 *******************************************************************************/
grammar InternalSTL;

options {
	superClass=AbstractInternalContentAssistParser;
	backtrack=true;
}

@lexer::header {
package xtext.ide.contentassist.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.ide.editor.contentassist.antlr.internal.Lexer;
}

@parser::header {
package xtext.ide.contentassist.antlr.internal;

import java.io.InputStream;
import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.ide.editor.contentassist.antlr.internal.AbstractInternalContentAssistParser;
import org.eclipse.xtext.ide.editor.contentassist.antlr.internal.DFA;
import xtext.services.STLGrammarAccess;

}
@parser::members {
	private STLGrammarAccess grammarAccess;

	public void setGrammarAccess(STLGrammarAccess grammarAccess) {
		this.grammarAccess = grammarAccess;
	}

	@Override
	protected Grammar getGrammar() {
		return grammarAccess.getGrammar();
	}

	@Override
	protected String getValueForTokenName(String tokenName) {
		return tokenName;
	}
}

// Entry rule entryRuleGeometry
entryRuleGeometry
:
{ before(grammarAccess.getGeometryRule()); }
	 ruleGeometry
{ after(grammarAccess.getGeometryRule()); } 
	 EOF 
;

// Rule Geometry
ruleGeometry 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getGeometryAccess().getGroup()); }
		(rule__Geometry__Group__0)
		{ after(grammarAccess.getGeometryAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleShape_Impl
entryRuleShape_Impl
:
{ before(grammarAccess.getShape_ImplRule()); }
	 ruleShape_Impl
{ after(grammarAccess.getShape_ImplRule()); } 
	 EOF 
;

// Rule Shape_Impl
ruleShape_Impl 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getShape_ImplAccess().getGroup()); }
		(rule__Shape_Impl__Group__0)
		{ after(grammarAccess.getShape_ImplAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleTriangle
entryRuleTriangle
:
{ before(grammarAccess.getTriangleRule()); }
	 ruleTriangle
{ after(grammarAccess.getTriangleRule()); } 
	 EOF 
;

// Rule Triangle
ruleTriangle 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getTriangleAccess().getGroup()); }
		(rule__Triangle__Group__0)
		{ after(grammarAccess.getTriangleAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleVertex
entryRuleVertex
:
{ before(grammarAccess.getVertexRule()); }
	 ruleVertex
{ after(grammarAccess.getVertexRule()); } 
	 EOF 
;

// Rule Vertex
ruleVertex 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getVertexAccess().getGroup()); }
		(rule__Vertex__Group__0)
		{ after(grammarAccess.getVertexAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleEDouble
entryRuleEDouble
:
{ before(grammarAccess.getEDoubleRule()); }
	 ruleEDouble
{ after(grammarAccess.getEDoubleRule()); } 
	 EOF 
;

// Rule EDouble
ruleEDouble 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getEDoubleAccess().getGroup()); }
		(rule__EDouble__Group__0)
		{ after(grammarAccess.getEDoubleAccess().getGroup()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

// Entry rule entryRuleELine
entryRuleELine
:
{ before(grammarAccess.getELineRule()); }
	 ruleELine
{ after(grammarAccess.getELineRule()); } 
	 EOF 
;

// Rule ELine
ruleELine 
	@init {
		int stackSize = keepStackSize();
	}
	:
	(
		{ before(grammarAccess.getELineAccess().getAlternatives()); }
		(rule__ELine__Alternatives)
		{ after(grammarAccess.getELineAccess().getAlternatives()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__EDouble__Alternatives_0
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getEDoubleAccess().getHyphenMinusKeyword_0_0()); }
		'-'
		{ after(grammarAccess.getEDoubleAccess().getHyphenMinusKeyword_0_0()); }
	)
	|
	(
		{ before(grammarAccess.getEDoubleAccess().getPlusSignKeyword_0_1()); }
		'+'
		{ after(grammarAccess.getEDoubleAccess().getPlusSignKeyword_0_1()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__EDouble__Alternatives_4_0
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getEDoubleAccess().getEKeyword_4_0_0()); }
		'E'
		{ after(grammarAccess.getEDoubleAccess().getEKeyword_4_0_0()); }
	)
	|
	(
		{ before(grammarAccess.getEDoubleAccess().getEKeyword_4_0_1()); }
		'e'
		{ after(grammarAccess.getEDoubleAccess().getEKeyword_4_0_1()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__EDouble__Alternatives_4_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getEDoubleAccess().getHyphenMinusKeyword_4_1_0()); }
		'-'
		{ after(grammarAccess.getEDoubleAccess().getHyphenMinusKeyword_4_1_0()); }
	)
	|
	(
		{ before(grammarAccess.getEDoubleAccess().getPlusSignKeyword_4_1_1()); }
		'+'
		{ after(grammarAccess.getEDoubleAccess().getPlusSignKeyword_4_1_1()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__ELine__Alternatives
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getELineAccess().getSTRINGTerminalRuleCall_0()); }
		RULE_STRING
		{ after(grammarAccess.getELineAccess().getSTRINGTerminalRuleCall_0()); }
	)
	|
	(
		{ before(grammarAccess.getELineAccess().getGroup_1()); }
		(rule__ELine__Group_1__0)
		{ after(grammarAccess.getELineAccess().getGroup_1()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__ELine__Alternatives_1_1_0
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getELineAccess().getControl000dKeyword_1_1_0_0()); }
		('\r')?
		{ after(grammarAccess.getELineAccess().getControl000dKeyword_1_1_0_0()); }
	)
	|
	(
		{ before(grammarAccess.getELineAccess().getControl000aKeyword_1_1_0_1()); }
		'\n'
		{ after(grammarAccess.getELineAccess().getControl000aKeyword_1_1_0_1()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__Geometry__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Geometry__Group__0__Impl
	rule__Geometry__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__Geometry__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getGeometryAccess().getGeometryAction_0()); }
	()
	{ after(grammarAccess.getGeometryAccess().getGeometryAction_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Geometry__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Geometry__Group__1__Impl
	rule__Geometry__Group__2
;
finally {
	restoreStackSize(stackSize);
}

rule__Geometry__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getGeometryAccess().getSolidKeyword_1()); }
	'solid'
	{ after(grammarAccess.getGeometryAccess().getSolidKeyword_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Geometry__Group__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Geometry__Group__2__Impl
	rule__Geometry__Group__3
;
finally {
	restoreStackSize(stackSize);
}

rule__Geometry__Group__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getGeometryAccess().getNameAssignment_2()); }
	(rule__Geometry__NameAssignment_2)?
	{ after(grammarAccess.getGeometryAccess().getNameAssignment_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Geometry__Group__3
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Geometry__Group__3__Impl
	rule__Geometry__Group__4
;
finally {
	restoreStackSize(stackSize);
}

rule__Geometry__Group__3__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getGeometryAccess().getNodesAssignment_3()); }
	(rule__Geometry__NodesAssignment_3)
	{ after(grammarAccess.getGeometryAccess().getNodesAssignment_3()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Geometry__Group__4
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Geometry__Group__4__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__Geometry__Group__4__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getGeometryAccess().getEndsolidKeyword_4()); }
	'endsolid'
	{ after(grammarAccess.getGeometryAccess().getEndsolidKeyword_4()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__Shape_Impl__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Shape_Impl__Group__0__Impl
	rule__Shape_Impl__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__Shape_Impl__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getShape_ImplAccess().getShapeAction_0()); }
	()
	{ after(grammarAccess.getShape_ImplAccess().getShapeAction_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Shape_Impl__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Shape_Impl__Group__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__Shape_Impl__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getShape_ImplAccess().getTrianglesAssignment_1()); }
	(rule__Shape_Impl__TrianglesAssignment_1)*
	{ after(grammarAccess.getShape_ImplAccess().getTrianglesAssignment_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__Triangle__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Triangle__Group__0__Impl
	rule__Triangle__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__Triangle__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getTriangleAccess().getTriangleAction_0()); }
	()
	{ after(grammarAccess.getTriangleAccess().getTriangleAction_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Triangle__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Triangle__Group__1__Impl
	rule__Triangle__Group__2
;
finally {
	restoreStackSize(stackSize);
}

rule__Triangle__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getTriangleAccess().getFacetKeyword_1()); }
	'facet'
	{ after(grammarAccess.getTriangleAccess().getFacetKeyword_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Triangle__Group__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Triangle__Group__2__Impl
	rule__Triangle__Group__3
;
finally {
	restoreStackSize(stackSize);
}

rule__Triangle__Group__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getTriangleAccess().getGroup_2()); }
	(rule__Triangle__Group_2__0)
	{ after(grammarAccess.getTriangleAccess().getGroup_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Triangle__Group__3
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Triangle__Group__3__Impl
	rule__Triangle__Group__4
;
finally {
	restoreStackSize(stackSize);
}

rule__Triangle__Group__3__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getTriangleAccess().getOuterKeyword_3()); }
	'outer'
	{ after(grammarAccess.getTriangleAccess().getOuterKeyword_3()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Triangle__Group__4
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Triangle__Group__4__Impl
	rule__Triangle__Group__5
;
finally {
	restoreStackSize(stackSize);
}

rule__Triangle__Group__4__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getTriangleAccess().getLoopKeyword_4()); }
	'loop'
	{ after(grammarAccess.getTriangleAccess().getLoopKeyword_4()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Triangle__Group__5
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Triangle__Group__5__Impl
	rule__Triangle__Group__6
;
finally {
	restoreStackSize(stackSize);
}

rule__Triangle__Group__5__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getTriangleAccess().getVerticesAssignment_5()); }
	(rule__Triangle__VerticesAssignment_5)*
	{ after(grammarAccess.getTriangleAccess().getVerticesAssignment_5()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Triangle__Group__6
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Triangle__Group__6__Impl
	rule__Triangle__Group__7
;
finally {
	restoreStackSize(stackSize);
}

rule__Triangle__Group__6__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getTriangleAccess().getEndloopKeyword_6()); }
	'endloop'
	{ after(grammarAccess.getTriangleAccess().getEndloopKeyword_6()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Triangle__Group__7
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Triangle__Group__7__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__Triangle__Group__7__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getTriangleAccess().getEndfacetKeyword_7()); }
	'endfacet'
	{ after(grammarAccess.getTriangleAccess().getEndfacetKeyword_7()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__Triangle__Group_2__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Triangle__Group_2__0__Impl
	rule__Triangle__Group_2__1
;
finally {
	restoreStackSize(stackSize);
}

rule__Triangle__Group_2__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getTriangleAccess().getNormalKeyword_2_0()); }
	'normal'
	{ after(grammarAccess.getTriangleAccess().getNormalKeyword_2_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Triangle__Group_2__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Triangle__Group_2__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__Triangle__Group_2__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getTriangleAccess().getNormalAssignment_2_1()); }
	(rule__Triangle__NormalAssignment_2_1)
	{ after(grammarAccess.getTriangleAccess().getNormalAssignment_2_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__Vertex__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Vertex__Group__0__Impl
	rule__Vertex__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__Vertex__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getVertexAccess().getVertexAction_0()); }
	()
	{ after(grammarAccess.getVertexAccess().getVertexAction_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Vertex__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Vertex__Group__1__Impl
	rule__Vertex__Group__2
;
finally {
	restoreStackSize(stackSize);
}

rule__Vertex__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getVertexAccess().getVertexKeyword_1()); }
	'vertex'
	{ after(grammarAccess.getVertexAccess().getVertexKeyword_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Vertex__Group__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Vertex__Group__2__Impl
	rule__Vertex__Group__3
;
finally {
	restoreStackSize(stackSize);
}

rule__Vertex__Group__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getVertexAccess().getXAssignment_2()); }
	(rule__Vertex__XAssignment_2)
	{ after(grammarAccess.getVertexAccess().getXAssignment_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Vertex__Group__3
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Vertex__Group__3__Impl
	rule__Vertex__Group__4
;
finally {
	restoreStackSize(stackSize);
}

rule__Vertex__Group__3__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getVertexAccess().getYAssignment_3()); }
	(rule__Vertex__YAssignment_3)
	{ after(grammarAccess.getVertexAccess().getYAssignment_3()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__Vertex__Group__4
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__Vertex__Group__4__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__Vertex__Group__4__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getVertexAccess().getZAssignment_4()); }
	(rule__Vertex__ZAssignment_4)
	{ after(grammarAccess.getVertexAccess().getZAssignment_4()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__EDouble__Group__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__EDouble__Group__0__Impl
	rule__EDouble__Group__1
;
finally {
	restoreStackSize(stackSize);
}

rule__EDouble__Group__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getEDoubleAccess().getAlternatives_0()); }
	(rule__EDouble__Alternatives_0)?
	{ after(grammarAccess.getEDoubleAccess().getAlternatives_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__EDouble__Group__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__EDouble__Group__1__Impl
	rule__EDouble__Group__2
;
finally {
	restoreStackSize(stackSize);
}

rule__EDouble__Group__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getEDoubleAccess().getINTTerminalRuleCall_1()); }
	(RULE_INT)?
	{ after(grammarAccess.getEDoubleAccess().getINTTerminalRuleCall_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__EDouble__Group__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__EDouble__Group__2__Impl
	rule__EDouble__Group__3
;
finally {
	restoreStackSize(stackSize);
}

rule__EDouble__Group__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getEDoubleAccess().getFullStopKeyword_2()); }
	'.'
	{ after(grammarAccess.getEDoubleAccess().getFullStopKeyword_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__EDouble__Group__3
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__EDouble__Group__3__Impl
	rule__EDouble__Group__4
;
finally {
	restoreStackSize(stackSize);
}

rule__EDouble__Group__3__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getEDoubleAccess().getINTTerminalRuleCall_3()); }
	RULE_INT
	{ after(grammarAccess.getEDoubleAccess().getINTTerminalRuleCall_3()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__EDouble__Group__4
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__EDouble__Group__4__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__EDouble__Group__4__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getEDoubleAccess().getGroup_4()); }
	(rule__EDouble__Group_4__0)?
	{ after(grammarAccess.getEDoubleAccess().getGroup_4()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__EDouble__Group_4__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__EDouble__Group_4__0__Impl
	rule__EDouble__Group_4__1
;
finally {
	restoreStackSize(stackSize);
}

rule__EDouble__Group_4__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getEDoubleAccess().getAlternatives_4_0()); }
	(rule__EDouble__Alternatives_4_0)
	{ after(grammarAccess.getEDoubleAccess().getAlternatives_4_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__EDouble__Group_4__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__EDouble__Group_4__1__Impl
	rule__EDouble__Group_4__2
;
finally {
	restoreStackSize(stackSize);
}

rule__EDouble__Group_4__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getEDoubleAccess().getAlternatives_4_1()); }
	(rule__EDouble__Alternatives_4_1)?
	{ after(grammarAccess.getEDoubleAccess().getAlternatives_4_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__EDouble__Group_4__2
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__EDouble__Group_4__2__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__EDouble__Group_4__2__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getEDoubleAccess().getINTTerminalRuleCall_4_2()); }
	RULE_INT
	{ after(grammarAccess.getEDoubleAccess().getINTTerminalRuleCall_4_2()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__ELine__Group_1__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ELine__Group_1__0__Impl
	rule__ELine__Group_1__1
;
finally {
	restoreStackSize(stackSize);
}

rule__ELine__Group_1__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getELineAccess().getIDTerminalRuleCall_1_0()); }
	RULE_ID
	{ after(grammarAccess.getELineAccess().getIDTerminalRuleCall_1_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}

rule__ELine__Group_1__1
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ELine__Group_1__1__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__ELine__Group_1__1__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getELineAccess().getGroup_1_1()); }
	(rule__ELine__Group_1_1__0)
	{ after(grammarAccess.getELineAccess().getGroup_1_1()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__ELine__Group_1_1__0
	@init {
		int stackSize = keepStackSize();
	}
:
	rule__ELine__Group_1_1__0__Impl
;
finally {
	restoreStackSize(stackSize);
}

rule__ELine__Group_1_1__0__Impl
	@init {
		int stackSize = keepStackSize();
	}
:
(
	{ before(grammarAccess.getELineAccess().getAlternatives_1_1_0()); }
	(rule__ELine__Alternatives_1_1_0)
	{ after(grammarAccess.getELineAccess().getAlternatives_1_1_0()); }
)
;
finally {
	restoreStackSize(stackSize);
}


rule__Geometry__NameAssignment_2
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getGeometryAccess().getNameELineParserRuleCall_2_0()); }
		ruleELine
		{ after(grammarAccess.getGeometryAccess().getNameELineParserRuleCall_2_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__Geometry__NodesAssignment_3
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getGeometryAccess().getNodesShape_ImplParserRuleCall_3_0()); }
		ruleShape_Impl
		{ after(grammarAccess.getGeometryAccess().getNodesShape_ImplParserRuleCall_3_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__Shape_Impl__TrianglesAssignment_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getShape_ImplAccess().getTrianglesTriangleParserRuleCall_1_0()); }
		ruleTriangle
		{ after(grammarAccess.getShape_ImplAccess().getTrianglesTriangleParserRuleCall_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__Triangle__NormalAssignment_2_1
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getTriangleAccess().getNormalVertexParserRuleCall_2_1_0()); }
		ruleVertex
		{ after(grammarAccess.getTriangleAccess().getNormalVertexParserRuleCall_2_1_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__Triangle__VerticesAssignment_5
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getTriangleAccess().getVerticesVertexParserRuleCall_5_0()); }
		ruleVertex
		{ after(grammarAccess.getTriangleAccess().getVerticesVertexParserRuleCall_5_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__Vertex__XAssignment_2
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getVertexAccess().getXEDoubleParserRuleCall_2_0()); }
		ruleEDouble
		{ after(grammarAccess.getVertexAccess().getXEDoubleParserRuleCall_2_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__Vertex__YAssignment_3
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getVertexAccess().getYEDoubleParserRuleCall_3_0()); }
		ruleEDouble
		{ after(grammarAccess.getVertexAccess().getYEDoubleParserRuleCall_3_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

rule__Vertex__ZAssignment_4
	@init {
		int stackSize = keepStackSize();
	}
:
	(
		{ before(grammarAccess.getVertexAccess().getZEDoubleParserRuleCall_4_0()); }
		ruleEDouble
		{ after(grammarAccess.getVertexAccess().getZEDoubleParserRuleCall_4_0()); }
	)
;
finally {
	restoreStackSize(stackSize);
}

RULE_ID : '^'? ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*;

RULE_INT : ('0'..'9')+;

RULE_STRING : ('"' ('\\' .|~(('\\'|'"')))* '"'|'\'' ('\\' .|~(('\\'|'\'')))* '\'');

RULE_ML_COMMENT : '/*' ( options {greedy=false;} : . )*'*/';

RULE_SL_COMMENT : '//' ~(('\n'|'\r'))* ('\r'? '\n')?;

RULE_WS : (' '|'\t'|'\r'|'\n')+;

RULE_ANY_OTHER : .;
