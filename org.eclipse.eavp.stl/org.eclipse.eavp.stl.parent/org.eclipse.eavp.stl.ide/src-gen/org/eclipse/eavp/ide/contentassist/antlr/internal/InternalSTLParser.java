package org.eclipse.eavp.ide.contentassist.antlr.internal;

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
import org.eclipse.eavp.services.STLGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalSTLParser extends AbstractInternalContentAssistParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_ID", "RULE_DOUBLE", "RULE_INT", "RULE_STRING", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'solid'", "'endsolid'", "'facet'", "'endfacet'", "'normal'", "'outer'", "'loop'", "'endloop'", "'vertex'"
    };
    public static final int RULE_STRING=7;
    public static final int RULE_SL_COMMENT=9;
    public static final int T__19=19;
    public static final int T__15=15;
    public static final int RULE_DOUBLE=5;
    public static final int T__16=16;
    public static final int T__17=17;
    public static final int T__18=18;
    public static final int T__12=12;
    public static final int T__13=13;
    public static final int T__14=14;
    public static final int EOF=-1;
    public static final int RULE_ID=4;
    public static final int RULE_WS=10;
    public static final int RULE_ANY_OTHER=11;
    public static final int RULE_INT=6;
    public static final int RULE_ML_COMMENT=8;
    public static final int T__20=20;

    // delegates
    // delegators


        public InternalSTLParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalSTLParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalSTLParser.tokenNames; }
    public String getGrammarFileName() { return "InternalSTL.g"; }


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



    // $ANTLR start "entryRuleModel"
    // InternalSTL.g:53:1: entryRuleModel : ruleModel EOF ;
    public final void entryRuleModel() throws RecognitionException {
        try {
            // InternalSTL.g:54:1: ( ruleModel EOF )
            // InternalSTL.g:55:1: ruleModel EOF
            {
             before(grammarAccess.getModelRule()); 
            pushFollow(FOLLOW_1);
            ruleModel();

            state._fsp--;

             after(grammarAccess.getModelRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleModel"


    // $ANTLR start "ruleModel"
    // InternalSTL.g:62:1: ruleModel : ( ( rule__Model__Group__0 ) ) ;
    public final void ruleModel() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:66:2: ( ( ( rule__Model__Group__0 ) ) )
            // InternalSTL.g:67:2: ( ( rule__Model__Group__0 ) )
            {
            // InternalSTL.g:67:2: ( ( rule__Model__Group__0 ) )
            // InternalSTL.g:68:3: ( rule__Model__Group__0 )
            {
             before(grammarAccess.getModelAccess().getGroup()); 
            // InternalSTL.g:69:3: ( rule__Model__Group__0 )
            // InternalSTL.g:69:4: rule__Model__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Model__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getModelAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleModel"


    // $ANTLR start "entryRuleTriangle"
    // InternalSTL.g:78:1: entryRuleTriangle : ruleTriangle EOF ;
    public final void entryRuleTriangle() throws RecognitionException {
        try {
            // InternalSTL.g:79:1: ( ruleTriangle EOF )
            // InternalSTL.g:80:1: ruleTriangle EOF
            {
             before(grammarAccess.getTriangleRule()); 
            pushFollow(FOLLOW_1);
            ruleTriangle();

            state._fsp--;

             after(grammarAccess.getTriangleRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleTriangle"


    // $ANTLR start "ruleTriangle"
    // InternalSTL.g:87:1: ruleTriangle : ( ( rule__Triangle__Group__0 ) ) ;
    public final void ruleTriangle() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:91:2: ( ( ( rule__Triangle__Group__0 ) ) )
            // InternalSTL.g:92:2: ( ( rule__Triangle__Group__0 ) )
            {
            // InternalSTL.g:92:2: ( ( rule__Triangle__Group__0 ) )
            // InternalSTL.g:93:3: ( rule__Triangle__Group__0 )
            {
             before(grammarAccess.getTriangleAccess().getGroup()); 
            // InternalSTL.g:94:3: ( rule__Triangle__Group__0 )
            // InternalSTL.g:94:4: rule__Triangle__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Triangle__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getTriangleAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleTriangle"


    // $ANTLR start "entryRuleNormal"
    // InternalSTL.g:103:1: entryRuleNormal : ruleNormal EOF ;
    public final void entryRuleNormal() throws RecognitionException {
        try {
            // InternalSTL.g:104:1: ( ruleNormal EOF )
            // InternalSTL.g:105:1: ruleNormal EOF
            {
             before(grammarAccess.getNormalRule()); 
            pushFollow(FOLLOW_1);
            ruleNormal();

            state._fsp--;

             after(grammarAccess.getNormalRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleNormal"


    // $ANTLR start "ruleNormal"
    // InternalSTL.g:112:1: ruleNormal : ( ( rule__Normal__Group__0 ) ) ;
    public final void ruleNormal() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:116:2: ( ( ( rule__Normal__Group__0 ) ) )
            // InternalSTL.g:117:2: ( ( rule__Normal__Group__0 ) )
            {
            // InternalSTL.g:117:2: ( ( rule__Normal__Group__0 ) )
            // InternalSTL.g:118:3: ( rule__Normal__Group__0 )
            {
             before(grammarAccess.getNormalAccess().getGroup()); 
            // InternalSTL.g:119:3: ( rule__Normal__Group__0 )
            // InternalSTL.g:119:4: rule__Normal__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Normal__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getNormalAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleNormal"


    // $ANTLR start "entryRuleVector"
    // InternalSTL.g:128:1: entryRuleVector : ruleVector EOF ;
    public final void entryRuleVector() throws RecognitionException {
        try {
            // InternalSTL.g:129:1: ( ruleVector EOF )
            // InternalSTL.g:130:1: ruleVector EOF
            {
             before(grammarAccess.getVectorRule()); 
            pushFollow(FOLLOW_1);
            ruleVector();

            state._fsp--;

             after(grammarAccess.getVectorRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleVector"


    // $ANTLR start "ruleVector"
    // InternalSTL.g:137:1: ruleVector : ( ( rule__Vector__Group__0 ) ) ;
    public final void ruleVector() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:141:2: ( ( ( rule__Vector__Group__0 ) ) )
            // InternalSTL.g:142:2: ( ( rule__Vector__Group__0 ) )
            {
            // InternalSTL.g:142:2: ( ( rule__Vector__Group__0 ) )
            // InternalSTL.g:143:3: ( rule__Vector__Group__0 )
            {
             before(grammarAccess.getVectorAccess().getGroup()); 
            // InternalSTL.g:144:3: ( rule__Vector__Group__0 )
            // InternalSTL.g:144:4: rule__Vector__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Vector__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getVectorAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleVector"


    // $ANTLR start "entryRuleVerticies"
    // InternalSTL.g:153:1: entryRuleVerticies : ruleVerticies EOF ;
    public final void entryRuleVerticies() throws RecognitionException {
        try {
            // InternalSTL.g:154:1: ( ruleVerticies EOF )
            // InternalSTL.g:155:1: ruleVerticies EOF
            {
             before(grammarAccess.getVerticiesRule()); 
            pushFollow(FOLLOW_1);
            ruleVerticies();

            state._fsp--;

             after(grammarAccess.getVerticiesRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleVerticies"


    // $ANTLR start "ruleVerticies"
    // InternalSTL.g:162:1: ruleVerticies : ( ( rule__Verticies__Group__0 ) ) ;
    public final void ruleVerticies() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:166:2: ( ( ( rule__Verticies__Group__0 ) ) )
            // InternalSTL.g:167:2: ( ( rule__Verticies__Group__0 ) )
            {
            // InternalSTL.g:167:2: ( ( rule__Verticies__Group__0 ) )
            // InternalSTL.g:168:3: ( rule__Verticies__Group__0 )
            {
             before(grammarAccess.getVerticiesAccess().getGroup()); 
            // InternalSTL.g:169:3: ( rule__Verticies__Group__0 )
            // InternalSTL.g:169:4: rule__Verticies__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Verticies__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getVerticiesAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleVerticies"


    // $ANTLR start "entryRuleVertex"
    // InternalSTL.g:178:1: entryRuleVertex : ruleVertex EOF ;
    public final void entryRuleVertex() throws RecognitionException {
        try {
            // InternalSTL.g:179:1: ( ruleVertex EOF )
            // InternalSTL.g:180:1: ruleVertex EOF
            {
             before(grammarAccess.getVertexRule()); 
            pushFollow(FOLLOW_1);
            ruleVertex();

            state._fsp--;

             after(grammarAccess.getVertexRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleVertex"


    // $ANTLR start "ruleVertex"
    // InternalSTL.g:187:1: ruleVertex : ( ( rule__Vertex__Group__0 ) ) ;
    public final void ruleVertex() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:191:2: ( ( ( rule__Vertex__Group__0 ) ) )
            // InternalSTL.g:192:2: ( ( rule__Vertex__Group__0 ) )
            {
            // InternalSTL.g:192:2: ( ( rule__Vertex__Group__0 ) )
            // InternalSTL.g:193:3: ( rule__Vertex__Group__0 )
            {
             before(grammarAccess.getVertexAccess().getGroup()); 
            // InternalSTL.g:194:3: ( rule__Vertex__Group__0 )
            // InternalSTL.g:194:4: rule__Vertex__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Vertex__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getVertexAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleVertex"


    // $ANTLR start "rule__Model__Group__0"
    // InternalSTL.g:202:1: rule__Model__Group__0 : rule__Model__Group__0__Impl rule__Model__Group__1 ;
    public final void rule__Model__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:206:1: ( rule__Model__Group__0__Impl rule__Model__Group__1 )
            // InternalSTL.g:207:2: rule__Model__Group__0__Impl rule__Model__Group__1
            {
            pushFollow(FOLLOW_3);
            rule__Model__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Model__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Model__Group__0"


    // $ANTLR start "rule__Model__Group__0__Impl"
    // InternalSTL.g:214:1: rule__Model__Group__0__Impl : ( 'solid' ) ;
    public final void rule__Model__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:218:1: ( ( 'solid' ) )
            // InternalSTL.g:219:1: ( 'solid' )
            {
            // InternalSTL.g:219:1: ( 'solid' )
            // InternalSTL.g:220:2: 'solid'
            {
             before(grammarAccess.getModelAccess().getSolidKeyword_0()); 
            match(input,12,FOLLOW_2); 
             after(grammarAccess.getModelAccess().getSolidKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Model__Group__0__Impl"


    // $ANTLR start "rule__Model__Group__1"
    // InternalSTL.g:229:1: rule__Model__Group__1 : rule__Model__Group__1__Impl rule__Model__Group__2 ;
    public final void rule__Model__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:233:1: ( rule__Model__Group__1__Impl rule__Model__Group__2 )
            // InternalSTL.g:234:2: rule__Model__Group__1__Impl rule__Model__Group__2
            {
            pushFollow(FOLLOW_3);
            rule__Model__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Model__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Model__Group__1"


    // $ANTLR start "rule__Model__Group__1__Impl"
    // InternalSTL.g:241:1: rule__Model__Group__1__Impl : ( ( rule__Model__NameAssignment_1 )? ) ;
    public final void rule__Model__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:245:1: ( ( ( rule__Model__NameAssignment_1 )? ) )
            // InternalSTL.g:246:1: ( ( rule__Model__NameAssignment_1 )? )
            {
            // InternalSTL.g:246:1: ( ( rule__Model__NameAssignment_1 )? )
            // InternalSTL.g:247:2: ( rule__Model__NameAssignment_1 )?
            {
             before(grammarAccess.getModelAccess().getNameAssignment_1()); 
            // InternalSTL.g:248:2: ( rule__Model__NameAssignment_1 )?
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==RULE_ID) ) {
                alt1=1;
            }
            switch (alt1) {
                case 1 :
                    // InternalSTL.g:248:3: rule__Model__NameAssignment_1
                    {
                    pushFollow(FOLLOW_2);
                    rule__Model__NameAssignment_1();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getModelAccess().getNameAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Model__Group__1__Impl"


    // $ANTLR start "rule__Model__Group__2"
    // InternalSTL.g:256:1: rule__Model__Group__2 : rule__Model__Group__2__Impl rule__Model__Group__3 ;
    public final void rule__Model__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:260:1: ( rule__Model__Group__2__Impl rule__Model__Group__3 )
            // InternalSTL.g:261:2: rule__Model__Group__2__Impl rule__Model__Group__3
            {
            pushFollow(FOLLOW_3);
            rule__Model__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Model__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Model__Group__2"


    // $ANTLR start "rule__Model__Group__2__Impl"
    // InternalSTL.g:268:1: rule__Model__Group__2__Impl : ( ( rule__Model__TrianglesAssignment_2 )* ) ;
    public final void rule__Model__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:272:1: ( ( ( rule__Model__TrianglesAssignment_2 )* ) )
            // InternalSTL.g:273:1: ( ( rule__Model__TrianglesAssignment_2 )* )
            {
            // InternalSTL.g:273:1: ( ( rule__Model__TrianglesAssignment_2 )* )
            // InternalSTL.g:274:2: ( rule__Model__TrianglesAssignment_2 )*
            {
             before(grammarAccess.getModelAccess().getTrianglesAssignment_2()); 
            // InternalSTL.g:275:2: ( rule__Model__TrianglesAssignment_2 )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==14) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // InternalSTL.g:275:3: rule__Model__TrianglesAssignment_2
            	    {
            	    pushFollow(FOLLOW_4);
            	    rule__Model__TrianglesAssignment_2();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);

             after(grammarAccess.getModelAccess().getTrianglesAssignment_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Model__Group__2__Impl"


    // $ANTLR start "rule__Model__Group__3"
    // InternalSTL.g:283:1: rule__Model__Group__3 : rule__Model__Group__3__Impl rule__Model__Group__4 ;
    public final void rule__Model__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:287:1: ( rule__Model__Group__3__Impl rule__Model__Group__4 )
            // InternalSTL.g:288:2: rule__Model__Group__3__Impl rule__Model__Group__4
            {
            pushFollow(FOLLOW_5);
            rule__Model__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Model__Group__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Model__Group__3"


    // $ANTLR start "rule__Model__Group__3__Impl"
    // InternalSTL.g:295:1: rule__Model__Group__3__Impl : ( 'endsolid' ) ;
    public final void rule__Model__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:299:1: ( ( 'endsolid' ) )
            // InternalSTL.g:300:1: ( 'endsolid' )
            {
            // InternalSTL.g:300:1: ( 'endsolid' )
            // InternalSTL.g:301:2: 'endsolid'
            {
             before(grammarAccess.getModelAccess().getEndsolidKeyword_3()); 
            match(input,13,FOLLOW_2); 
             after(grammarAccess.getModelAccess().getEndsolidKeyword_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Model__Group__3__Impl"


    // $ANTLR start "rule__Model__Group__4"
    // InternalSTL.g:310:1: rule__Model__Group__4 : rule__Model__Group__4__Impl ;
    public final void rule__Model__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:314:1: ( rule__Model__Group__4__Impl )
            // InternalSTL.g:315:2: rule__Model__Group__4__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Model__Group__4__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Model__Group__4"


    // $ANTLR start "rule__Model__Group__4__Impl"
    // InternalSTL.g:321:1: rule__Model__Group__4__Impl : ( RULE_ID ) ;
    public final void rule__Model__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:325:1: ( ( RULE_ID ) )
            // InternalSTL.g:326:1: ( RULE_ID )
            {
            // InternalSTL.g:326:1: ( RULE_ID )
            // InternalSTL.g:327:2: RULE_ID
            {
             before(grammarAccess.getModelAccess().getIDTerminalRuleCall_4()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getModelAccess().getIDTerminalRuleCall_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Model__Group__4__Impl"


    // $ANTLR start "rule__Triangle__Group__0"
    // InternalSTL.g:337:1: rule__Triangle__Group__0 : rule__Triangle__Group__0__Impl rule__Triangle__Group__1 ;
    public final void rule__Triangle__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:341:1: ( rule__Triangle__Group__0__Impl rule__Triangle__Group__1 )
            // InternalSTL.g:342:2: rule__Triangle__Group__0__Impl rule__Triangle__Group__1
            {
            pushFollow(FOLLOW_6);
            rule__Triangle__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Triangle__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Triangle__Group__0"


    // $ANTLR start "rule__Triangle__Group__0__Impl"
    // InternalSTL.g:349:1: rule__Triangle__Group__0__Impl : ( 'facet' ) ;
    public final void rule__Triangle__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:353:1: ( ( 'facet' ) )
            // InternalSTL.g:354:1: ( 'facet' )
            {
            // InternalSTL.g:354:1: ( 'facet' )
            // InternalSTL.g:355:2: 'facet'
            {
             before(grammarAccess.getTriangleAccess().getFacetKeyword_0()); 
            match(input,14,FOLLOW_2); 
             after(grammarAccess.getTriangleAccess().getFacetKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Triangle__Group__0__Impl"


    // $ANTLR start "rule__Triangle__Group__1"
    // InternalSTL.g:364:1: rule__Triangle__Group__1 : rule__Triangle__Group__1__Impl rule__Triangle__Group__2 ;
    public final void rule__Triangle__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:368:1: ( rule__Triangle__Group__1__Impl rule__Triangle__Group__2 )
            // InternalSTL.g:369:2: rule__Triangle__Group__1__Impl rule__Triangle__Group__2
            {
            pushFollow(FOLLOW_7);
            rule__Triangle__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Triangle__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Triangle__Group__1"


    // $ANTLR start "rule__Triangle__Group__1__Impl"
    // InternalSTL.g:376:1: rule__Triangle__Group__1__Impl : ( ( rule__Triangle__NormalAssignment_1 ) ) ;
    public final void rule__Triangle__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:380:1: ( ( ( rule__Triangle__NormalAssignment_1 ) ) )
            // InternalSTL.g:381:1: ( ( rule__Triangle__NormalAssignment_1 ) )
            {
            // InternalSTL.g:381:1: ( ( rule__Triangle__NormalAssignment_1 ) )
            // InternalSTL.g:382:2: ( rule__Triangle__NormalAssignment_1 )
            {
             before(grammarAccess.getTriangleAccess().getNormalAssignment_1()); 
            // InternalSTL.g:383:2: ( rule__Triangle__NormalAssignment_1 )
            // InternalSTL.g:383:3: rule__Triangle__NormalAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__Triangle__NormalAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getTriangleAccess().getNormalAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Triangle__Group__1__Impl"


    // $ANTLR start "rule__Triangle__Group__2"
    // InternalSTL.g:391:1: rule__Triangle__Group__2 : rule__Triangle__Group__2__Impl rule__Triangle__Group__3 ;
    public final void rule__Triangle__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:395:1: ( rule__Triangle__Group__2__Impl rule__Triangle__Group__3 )
            // InternalSTL.g:396:2: rule__Triangle__Group__2__Impl rule__Triangle__Group__3
            {
            pushFollow(FOLLOW_8);
            rule__Triangle__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Triangle__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Triangle__Group__2"


    // $ANTLR start "rule__Triangle__Group__2__Impl"
    // InternalSTL.g:403:1: rule__Triangle__Group__2__Impl : ( ( rule__Triangle__VerticiesAssignment_2 ) ) ;
    public final void rule__Triangle__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:407:1: ( ( ( rule__Triangle__VerticiesAssignment_2 ) ) )
            // InternalSTL.g:408:1: ( ( rule__Triangle__VerticiesAssignment_2 ) )
            {
            // InternalSTL.g:408:1: ( ( rule__Triangle__VerticiesAssignment_2 ) )
            // InternalSTL.g:409:2: ( rule__Triangle__VerticiesAssignment_2 )
            {
             before(grammarAccess.getTriangleAccess().getVerticiesAssignment_2()); 
            // InternalSTL.g:410:2: ( rule__Triangle__VerticiesAssignment_2 )
            // InternalSTL.g:410:3: rule__Triangle__VerticiesAssignment_2
            {
            pushFollow(FOLLOW_2);
            rule__Triangle__VerticiesAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getTriangleAccess().getVerticiesAssignment_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Triangle__Group__2__Impl"


    // $ANTLR start "rule__Triangle__Group__3"
    // InternalSTL.g:418:1: rule__Triangle__Group__3 : rule__Triangle__Group__3__Impl ;
    public final void rule__Triangle__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:422:1: ( rule__Triangle__Group__3__Impl )
            // InternalSTL.g:423:2: rule__Triangle__Group__3__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Triangle__Group__3__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Triangle__Group__3"


    // $ANTLR start "rule__Triangle__Group__3__Impl"
    // InternalSTL.g:429:1: rule__Triangle__Group__3__Impl : ( 'endfacet' ) ;
    public final void rule__Triangle__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:433:1: ( ( 'endfacet' ) )
            // InternalSTL.g:434:1: ( 'endfacet' )
            {
            // InternalSTL.g:434:1: ( 'endfacet' )
            // InternalSTL.g:435:2: 'endfacet'
            {
             before(grammarAccess.getTriangleAccess().getEndfacetKeyword_3()); 
            match(input,15,FOLLOW_2); 
             after(grammarAccess.getTriangleAccess().getEndfacetKeyword_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Triangle__Group__3__Impl"


    // $ANTLR start "rule__Normal__Group__0"
    // InternalSTL.g:445:1: rule__Normal__Group__0 : rule__Normal__Group__0__Impl rule__Normal__Group__1 ;
    public final void rule__Normal__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:449:1: ( rule__Normal__Group__0__Impl rule__Normal__Group__1 )
            // InternalSTL.g:450:2: rule__Normal__Group__0__Impl rule__Normal__Group__1
            {
            pushFollow(FOLLOW_9);
            rule__Normal__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Normal__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Normal__Group__0"


    // $ANTLR start "rule__Normal__Group__0__Impl"
    // InternalSTL.g:457:1: rule__Normal__Group__0__Impl : ( 'normal' ) ;
    public final void rule__Normal__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:461:1: ( ( 'normal' ) )
            // InternalSTL.g:462:1: ( 'normal' )
            {
            // InternalSTL.g:462:1: ( 'normal' )
            // InternalSTL.g:463:2: 'normal'
            {
             before(grammarAccess.getNormalAccess().getNormalKeyword_0()); 
            match(input,16,FOLLOW_2); 
             after(grammarAccess.getNormalAccess().getNormalKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Normal__Group__0__Impl"


    // $ANTLR start "rule__Normal__Group__1"
    // InternalSTL.g:472:1: rule__Normal__Group__1 : rule__Normal__Group__1__Impl ;
    public final void rule__Normal__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:476:1: ( rule__Normal__Group__1__Impl )
            // InternalSTL.g:477:2: rule__Normal__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Normal__Group__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Normal__Group__1"


    // $ANTLR start "rule__Normal__Group__1__Impl"
    // InternalSTL.g:483:1: rule__Normal__Group__1__Impl : ( ( rule__Normal__VectorAssignment_1 ) ) ;
    public final void rule__Normal__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:487:1: ( ( ( rule__Normal__VectorAssignment_1 ) ) )
            // InternalSTL.g:488:1: ( ( rule__Normal__VectorAssignment_1 ) )
            {
            // InternalSTL.g:488:1: ( ( rule__Normal__VectorAssignment_1 ) )
            // InternalSTL.g:489:2: ( rule__Normal__VectorAssignment_1 )
            {
             before(grammarAccess.getNormalAccess().getVectorAssignment_1()); 
            // InternalSTL.g:490:2: ( rule__Normal__VectorAssignment_1 )
            // InternalSTL.g:490:3: rule__Normal__VectorAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__Normal__VectorAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getNormalAccess().getVectorAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Normal__Group__1__Impl"


    // $ANTLR start "rule__Vector__Group__0"
    // InternalSTL.g:499:1: rule__Vector__Group__0 : rule__Vector__Group__0__Impl rule__Vector__Group__1 ;
    public final void rule__Vector__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:503:1: ( rule__Vector__Group__0__Impl rule__Vector__Group__1 )
            // InternalSTL.g:504:2: rule__Vector__Group__0__Impl rule__Vector__Group__1
            {
            pushFollow(FOLLOW_9);
            rule__Vector__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Vector__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Vector__Group__0"


    // $ANTLR start "rule__Vector__Group__0__Impl"
    // InternalSTL.g:511:1: rule__Vector__Group__0__Impl : ( ( rule__Vector__XAssignment_0 ) ) ;
    public final void rule__Vector__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:515:1: ( ( ( rule__Vector__XAssignment_0 ) ) )
            // InternalSTL.g:516:1: ( ( rule__Vector__XAssignment_0 ) )
            {
            // InternalSTL.g:516:1: ( ( rule__Vector__XAssignment_0 ) )
            // InternalSTL.g:517:2: ( rule__Vector__XAssignment_0 )
            {
             before(grammarAccess.getVectorAccess().getXAssignment_0()); 
            // InternalSTL.g:518:2: ( rule__Vector__XAssignment_0 )
            // InternalSTL.g:518:3: rule__Vector__XAssignment_0
            {
            pushFollow(FOLLOW_2);
            rule__Vector__XAssignment_0();

            state._fsp--;


            }

             after(grammarAccess.getVectorAccess().getXAssignment_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Vector__Group__0__Impl"


    // $ANTLR start "rule__Vector__Group__1"
    // InternalSTL.g:526:1: rule__Vector__Group__1 : rule__Vector__Group__1__Impl rule__Vector__Group__2 ;
    public final void rule__Vector__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:530:1: ( rule__Vector__Group__1__Impl rule__Vector__Group__2 )
            // InternalSTL.g:531:2: rule__Vector__Group__1__Impl rule__Vector__Group__2
            {
            pushFollow(FOLLOW_9);
            rule__Vector__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Vector__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Vector__Group__1"


    // $ANTLR start "rule__Vector__Group__1__Impl"
    // InternalSTL.g:538:1: rule__Vector__Group__1__Impl : ( ( rule__Vector__YAssignment_1 ) ) ;
    public final void rule__Vector__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:542:1: ( ( ( rule__Vector__YAssignment_1 ) ) )
            // InternalSTL.g:543:1: ( ( rule__Vector__YAssignment_1 ) )
            {
            // InternalSTL.g:543:1: ( ( rule__Vector__YAssignment_1 ) )
            // InternalSTL.g:544:2: ( rule__Vector__YAssignment_1 )
            {
             before(grammarAccess.getVectorAccess().getYAssignment_1()); 
            // InternalSTL.g:545:2: ( rule__Vector__YAssignment_1 )
            // InternalSTL.g:545:3: rule__Vector__YAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__Vector__YAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getVectorAccess().getYAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Vector__Group__1__Impl"


    // $ANTLR start "rule__Vector__Group__2"
    // InternalSTL.g:553:1: rule__Vector__Group__2 : rule__Vector__Group__2__Impl ;
    public final void rule__Vector__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:557:1: ( rule__Vector__Group__2__Impl )
            // InternalSTL.g:558:2: rule__Vector__Group__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Vector__Group__2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Vector__Group__2"


    // $ANTLR start "rule__Vector__Group__2__Impl"
    // InternalSTL.g:564:1: rule__Vector__Group__2__Impl : ( ( rule__Vector__ZAssignment_2 ) ) ;
    public final void rule__Vector__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:568:1: ( ( ( rule__Vector__ZAssignment_2 ) ) )
            // InternalSTL.g:569:1: ( ( rule__Vector__ZAssignment_2 ) )
            {
            // InternalSTL.g:569:1: ( ( rule__Vector__ZAssignment_2 ) )
            // InternalSTL.g:570:2: ( rule__Vector__ZAssignment_2 )
            {
             before(grammarAccess.getVectorAccess().getZAssignment_2()); 
            // InternalSTL.g:571:2: ( rule__Vector__ZAssignment_2 )
            // InternalSTL.g:571:3: rule__Vector__ZAssignment_2
            {
            pushFollow(FOLLOW_2);
            rule__Vector__ZAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getVectorAccess().getZAssignment_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Vector__Group__2__Impl"


    // $ANTLR start "rule__Verticies__Group__0"
    // InternalSTL.g:580:1: rule__Verticies__Group__0 : rule__Verticies__Group__0__Impl rule__Verticies__Group__1 ;
    public final void rule__Verticies__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:584:1: ( rule__Verticies__Group__0__Impl rule__Verticies__Group__1 )
            // InternalSTL.g:585:2: rule__Verticies__Group__0__Impl rule__Verticies__Group__1
            {
            pushFollow(FOLLOW_10);
            rule__Verticies__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Verticies__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Verticies__Group__0"


    // $ANTLR start "rule__Verticies__Group__0__Impl"
    // InternalSTL.g:592:1: rule__Verticies__Group__0__Impl : ( 'outer' ) ;
    public final void rule__Verticies__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:596:1: ( ( 'outer' ) )
            // InternalSTL.g:597:1: ( 'outer' )
            {
            // InternalSTL.g:597:1: ( 'outer' )
            // InternalSTL.g:598:2: 'outer'
            {
             before(grammarAccess.getVerticiesAccess().getOuterKeyword_0()); 
            match(input,17,FOLLOW_2); 
             after(grammarAccess.getVerticiesAccess().getOuterKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Verticies__Group__0__Impl"


    // $ANTLR start "rule__Verticies__Group__1"
    // InternalSTL.g:607:1: rule__Verticies__Group__1 : rule__Verticies__Group__1__Impl rule__Verticies__Group__2 ;
    public final void rule__Verticies__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:611:1: ( rule__Verticies__Group__1__Impl rule__Verticies__Group__2 )
            // InternalSTL.g:612:2: rule__Verticies__Group__1__Impl rule__Verticies__Group__2
            {
            pushFollow(FOLLOW_11);
            rule__Verticies__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Verticies__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Verticies__Group__1"


    // $ANTLR start "rule__Verticies__Group__1__Impl"
    // InternalSTL.g:619:1: rule__Verticies__Group__1__Impl : ( 'loop' ) ;
    public final void rule__Verticies__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:623:1: ( ( 'loop' ) )
            // InternalSTL.g:624:1: ( 'loop' )
            {
            // InternalSTL.g:624:1: ( 'loop' )
            // InternalSTL.g:625:2: 'loop'
            {
             before(grammarAccess.getVerticiesAccess().getLoopKeyword_1()); 
            match(input,18,FOLLOW_2); 
             after(grammarAccess.getVerticiesAccess().getLoopKeyword_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Verticies__Group__1__Impl"


    // $ANTLR start "rule__Verticies__Group__2"
    // InternalSTL.g:634:1: rule__Verticies__Group__2 : rule__Verticies__Group__2__Impl rule__Verticies__Group__3 ;
    public final void rule__Verticies__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:638:1: ( rule__Verticies__Group__2__Impl rule__Verticies__Group__3 )
            // InternalSTL.g:639:2: rule__Verticies__Group__2__Impl rule__Verticies__Group__3
            {
            pushFollow(FOLLOW_11);
            rule__Verticies__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Verticies__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Verticies__Group__2"


    // $ANTLR start "rule__Verticies__Group__2__Impl"
    // InternalSTL.g:646:1: rule__Verticies__Group__2__Impl : ( ( rule__Verticies__V1Assignment_2 ) ) ;
    public final void rule__Verticies__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:650:1: ( ( ( rule__Verticies__V1Assignment_2 ) ) )
            // InternalSTL.g:651:1: ( ( rule__Verticies__V1Assignment_2 ) )
            {
            // InternalSTL.g:651:1: ( ( rule__Verticies__V1Assignment_2 ) )
            // InternalSTL.g:652:2: ( rule__Verticies__V1Assignment_2 )
            {
             before(grammarAccess.getVerticiesAccess().getV1Assignment_2()); 
            // InternalSTL.g:653:2: ( rule__Verticies__V1Assignment_2 )
            // InternalSTL.g:653:3: rule__Verticies__V1Assignment_2
            {
            pushFollow(FOLLOW_2);
            rule__Verticies__V1Assignment_2();

            state._fsp--;


            }

             after(grammarAccess.getVerticiesAccess().getV1Assignment_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Verticies__Group__2__Impl"


    // $ANTLR start "rule__Verticies__Group__3"
    // InternalSTL.g:661:1: rule__Verticies__Group__3 : rule__Verticies__Group__3__Impl rule__Verticies__Group__4 ;
    public final void rule__Verticies__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:665:1: ( rule__Verticies__Group__3__Impl rule__Verticies__Group__4 )
            // InternalSTL.g:666:2: rule__Verticies__Group__3__Impl rule__Verticies__Group__4
            {
            pushFollow(FOLLOW_11);
            rule__Verticies__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Verticies__Group__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Verticies__Group__3"


    // $ANTLR start "rule__Verticies__Group__3__Impl"
    // InternalSTL.g:673:1: rule__Verticies__Group__3__Impl : ( ( rule__Verticies__V2Assignment_3 ) ) ;
    public final void rule__Verticies__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:677:1: ( ( ( rule__Verticies__V2Assignment_3 ) ) )
            // InternalSTL.g:678:1: ( ( rule__Verticies__V2Assignment_3 ) )
            {
            // InternalSTL.g:678:1: ( ( rule__Verticies__V2Assignment_3 ) )
            // InternalSTL.g:679:2: ( rule__Verticies__V2Assignment_3 )
            {
             before(grammarAccess.getVerticiesAccess().getV2Assignment_3()); 
            // InternalSTL.g:680:2: ( rule__Verticies__V2Assignment_3 )
            // InternalSTL.g:680:3: rule__Verticies__V2Assignment_3
            {
            pushFollow(FOLLOW_2);
            rule__Verticies__V2Assignment_3();

            state._fsp--;


            }

             after(grammarAccess.getVerticiesAccess().getV2Assignment_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Verticies__Group__3__Impl"


    // $ANTLR start "rule__Verticies__Group__4"
    // InternalSTL.g:688:1: rule__Verticies__Group__4 : rule__Verticies__Group__4__Impl rule__Verticies__Group__5 ;
    public final void rule__Verticies__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:692:1: ( rule__Verticies__Group__4__Impl rule__Verticies__Group__5 )
            // InternalSTL.g:693:2: rule__Verticies__Group__4__Impl rule__Verticies__Group__5
            {
            pushFollow(FOLLOW_12);
            rule__Verticies__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Verticies__Group__5();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Verticies__Group__4"


    // $ANTLR start "rule__Verticies__Group__4__Impl"
    // InternalSTL.g:700:1: rule__Verticies__Group__4__Impl : ( ( rule__Verticies__V3Assignment_4 ) ) ;
    public final void rule__Verticies__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:704:1: ( ( ( rule__Verticies__V3Assignment_4 ) ) )
            // InternalSTL.g:705:1: ( ( rule__Verticies__V3Assignment_4 ) )
            {
            // InternalSTL.g:705:1: ( ( rule__Verticies__V3Assignment_4 ) )
            // InternalSTL.g:706:2: ( rule__Verticies__V3Assignment_4 )
            {
             before(grammarAccess.getVerticiesAccess().getV3Assignment_4()); 
            // InternalSTL.g:707:2: ( rule__Verticies__V3Assignment_4 )
            // InternalSTL.g:707:3: rule__Verticies__V3Assignment_4
            {
            pushFollow(FOLLOW_2);
            rule__Verticies__V3Assignment_4();

            state._fsp--;


            }

             after(grammarAccess.getVerticiesAccess().getV3Assignment_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Verticies__Group__4__Impl"


    // $ANTLR start "rule__Verticies__Group__5"
    // InternalSTL.g:715:1: rule__Verticies__Group__5 : rule__Verticies__Group__5__Impl ;
    public final void rule__Verticies__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:719:1: ( rule__Verticies__Group__5__Impl )
            // InternalSTL.g:720:2: rule__Verticies__Group__5__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Verticies__Group__5__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Verticies__Group__5"


    // $ANTLR start "rule__Verticies__Group__5__Impl"
    // InternalSTL.g:726:1: rule__Verticies__Group__5__Impl : ( 'endloop' ) ;
    public final void rule__Verticies__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:730:1: ( ( 'endloop' ) )
            // InternalSTL.g:731:1: ( 'endloop' )
            {
            // InternalSTL.g:731:1: ( 'endloop' )
            // InternalSTL.g:732:2: 'endloop'
            {
             before(grammarAccess.getVerticiesAccess().getEndloopKeyword_5()); 
            match(input,19,FOLLOW_2); 
             after(grammarAccess.getVerticiesAccess().getEndloopKeyword_5()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Verticies__Group__5__Impl"


    // $ANTLR start "rule__Vertex__Group__0"
    // InternalSTL.g:742:1: rule__Vertex__Group__0 : rule__Vertex__Group__0__Impl rule__Vertex__Group__1 ;
    public final void rule__Vertex__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:746:1: ( rule__Vertex__Group__0__Impl rule__Vertex__Group__1 )
            // InternalSTL.g:747:2: rule__Vertex__Group__0__Impl rule__Vertex__Group__1
            {
            pushFollow(FOLLOW_9);
            rule__Vertex__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Vertex__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Vertex__Group__0"


    // $ANTLR start "rule__Vertex__Group__0__Impl"
    // InternalSTL.g:754:1: rule__Vertex__Group__0__Impl : ( 'vertex' ) ;
    public final void rule__Vertex__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:758:1: ( ( 'vertex' ) )
            // InternalSTL.g:759:1: ( 'vertex' )
            {
            // InternalSTL.g:759:1: ( 'vertex' )
            // InternalSTL.g:760:2: 'vertex'
            {
             before(grammarAccess.getVertexAccess().getVertexKeyword_0()); 
            match(input,20,FOLLOW_2); 
             after(grammarAccess.getVertexAccess().getVertexKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Vertex__Group__0__Impl"


    // $ANTLR start "rule__Vertex__Group__1"
    // InternalSTL.g:769:1: rule__Vertex__Group__1 : rule__Vertex__Group__1__Impl rule__Vertex__Group__2 ;
    public final void rule__Vertex__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:773:1: ( rule__Vertex__Group__1__Impl rule__Vertex__Group__2 )
            // InternalSTL.g:774:2: rule__Vertex__Group__1__Impl rule__Vertex__Group__2
            {
            pushFollow(FOLLOW_9);
            rule__Vertex__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Vertex__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Vertex__Group__1"


    // $ANTLR start "rule__Vertex__Group__1__Impl"
    // InternalSTL.g:781:1: rule__Vertex__Group__1__Impl : ( ( rule__Vertex__XAssignment_1 ) ) ;
    public final void rule__Vertex__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:785:1: ( ( ( rule__Vertex__XAssignment_1 ) ) )
            // InternalSTL.g:786:1: ( ( rule__Vertex__XAssignment_1 ) )
            {
            // InternalSTL.g:786:1: ( ( rule__Vertex__XAssignment_1 ) )
            // InternalSTL.g:787:2: ( rule__Vertex__XAssignment_1 )
            {
             before(grammarAccess.getVertexAccess().getXAssignment_1()); 
            // InternalSTL.g:788:2: ( rule__Vertex__XAssignment_1 )
            // InternalSTL.g:788:3: rule__Vertex__XAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__Vertex__XAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getVertexAccess().getXAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Vertex__Group__1__Impl"


    // $ANTLR start "rule__Vertex__Group__2"
    // InternalSTL.g:796:1: rule__Vertex__Group__2 : rule__Vertex__Group__2__Impl rule__Vertex__Group__3 ;
    public final void rule__Vertex__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:800:1: ( rule__Vertex__Group__2__Impl rule__Vertex__Group__3 )
            // InternalSTL.g:801:2: rule__Vertex__Group__2__Impl rule__Vertex__Group__3
            {
            pushFollow(FOLLOW_9);
            rule__Vertex__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Vertex__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Vertex__Group__2"


    // $ANTLR start "rule__Vertex__Group__2__Impl"
    // InternalSTL.g:808:1: rule__Vertex__Group__2__Impl : ( ( rule__Vertex__YAssignment_2 ) ) ;
    public final void rule__Vertex__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:812:1: ( ( ( rule__Vertex__YAssignment_2 ) ) )
            // InternalSTL.g:813:1: ( ( rule__Vertex__YAssignment_2 ) )
            {
            // InternalSTL.g:813:1: ( ( rule__Vertex__YAssignment_2 ) )
            // InternalSTL.g:814:2: ( rule__Vertex__YAssignment_2 )
            {
             before(grammarAccess.getVertexAccess().getYAssignment_2()); 
            // InternalSTL.g:815:2: ( rule__Vertex__YAssignment_2 )
            // InternalSTL.g:815:3: rule__Vertex__YAssignment_2
            {
            pushFollow(FOLLOW_2);
            rule__Vertex__YAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getVertexAccess().getYAssignment_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Vertex__Group__2__Impl"


    // $ANTLR start "rule__Vertex__Group__3"
    // InternalSTL.g:823:1: rule__Vertex__Group__3 : rule__Vertex__Group__3__Impl ;
    public final void rule__Vertex__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:827:1: ( rule__Vertex__Group__3__Impl )
            // InternalSTL.g:828:2: rule__Vertex__Group__3__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Vertex__Group__3__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Vertex__Group__3"


    // $ANTLR start "rule__Vertex__Group__3__Impl"
    // InternalSTL.g:834:1: rule__Vertex__Group__3__Impl : ( ( rule__Vertex__ZAssignment_3 ) ) ;
    public final void rule__Vertex__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:838:1: ( ( ( rule__Vertex__ZAssignment_3 ) ) )
            // InternalSTL.g:839:1: ( ( rule__Vertex__ZAssignment_3 ) )
            {
            // InternalSTL.g:839:1: ( ( rule__Vertex__ZAssignment_3 ) )
            // InternalSTL.g:840:2: ( rule__Vertex__ZAssignment_3 )
            {
             before(grammarAccess.getVertexAccess().getZAssignment_3()); 
            // InternalSTL.g:841:2: ( rule__Vertex__ZAssignment_3 )
            // InternalSTL.g:841:3: rule__Vertex__ZAssignment_3
            {
            pushFollow(FOLLOW_2);
            rule__Vertex__ZAssignment_3();

            state._fsp--;


            }

             after(grammarAccess.getVertexAccess().getZAssignment_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Vertex__Group__3__Impl"


    // $ANTLR start "rule__Model__NameAssignment_1"
    // InternalSTL.g:850:1: rule__Model__NameAssignment_1 : ( RULE_ID ) ;
    public final void rule__Model__NameAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:854:1: ( ( RULE_ID ) )
            // InternalSTL.g:855:2: ( RULE_ID )
            {
            // InternalSTL.g:855:2: ( RULE_ID )
            // InternalSTL.g:856:3: RULE_ID
            {
             before(grammarAccess.getModelAccess().getNameIDTerminalRuleCall_1_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getModelAccess().getNameIDTerminalRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Model__NameAssignment_1"


    // $ANTLR start "rule__Model__TrianglesAssignment_2"
    // InternalSTL.g:865:1: rule__Model__TrianglesAssignment_2 : ( ruleTriangle ) ;
    public final void rule__Model__TrianglesAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:869:1: ( ( ruleTriangle ) )
            // InternalSTL.g:870:2: ( ruleTriangle )
            {
            // InternalSTL.g:870:2: ( ruleTriangle )
            // InternalSTL.g:871:3: ruleTriangle
            {
             before(grammarAccess.getModelAccess().getTrianglesTriangleParserRuleCall_2_0()); 
            pushFollow(FOLLOW_2);
            ruleTriangle();

            state._fsp--;

             after(grammarAccess.getModelAccess().getTrianglesTriangleParserRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Model__TrianglesAssignment_2"


    // $ANTLR start "rule__Triangle__NormalAssignment_1"
    // InternalSTL.g:880:1: rule__Triangle__NormalAssignment_1 : ( ruleNormal ) ;
    public final void rule__Triangle__NormalAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:884:1: ( ( ruleNormal ) )
            // InternalSTL.g:885:2: ( ruleNormal )
            {
            // InternalSTL.g:885:2: ( ruleNormal )
            // InternalSTL.g:886:3: ruleNormal
            {
             before(grammarAccess.getTriangleAccess().getNormalNormalParserRuleCall_1_0()); 
            pushFollow(FOLLOW_2);
            ruleNormal();

            state._fsp--;

             after(grammarAccess.getTriangleAccess().getNormalNormalParserRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Triangle__NormalAssignment_1"


    // $ANTLR start "rule__Triangle__VerticiesAssignment_2"
    // InternalSTL.g:895:1: rule__Triangle__VerticiesAssignment_2 : ( ruleVerticies ) ;
    public final void rule__Triangle__VerticiesAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:899:1: ( ( ruleVerticies ) )
            // InternalSTL.g:900:2: ( ruleVerticies )
            {
            // InternalSTL.g:900:2: ( ruleVerticies )
            // InternalSTL.g:901:3: ruleVerticies
            {
             before(grammarAccess.getTriangleAccess().getVerticiesVerticiesParserRuleCall_2_0()); 
            pushFollow(FOLLOW_2);
            ruleVerticies();

            state._fsp--;

             after(grammarAccess.getTriangleAccess().getVerticiesVerticiesParserRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Triangle__VerticiesAssignment_2"


    // $ANTLR start "rule__Normal__VectorAssignment_1"
    // InternalSTL.g:910:1: rule__Normal__VectorAssignment_1 : ( ruleVector ) ;
    public final void rule__Normal__VectorAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:914:1: ( ( ruleVector ) )
            // InternalSTL.g:915:2: ( ruleVector )
            {
            // InternalSTL.g:915:2: ( ruleVector )
            // InternalSTL.g:916:3: ruleVector
            {
             before(grammarAccess.getNormalAccess().getVectorVectorParserRuleCall_1_0()); 
            pushFollow(FOLLOW_2);
            ruleVector();

            state._fsp--;

             after(grammarAccess.getNormalAccess().getVectorVectorParserRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Normal__VectorAssignment_1"


    // $ANTLR start "rule__Vector__XAssignment_0"
    // InternalSTL.g:925:1: rule__Vector__XAssignment_0 : ( RULE_DOUBLE ) ;
    public final void rule__Vector__XAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:929:1: ( ( RULE_DOUBLE ) )
            // InternalSTL.g:930:2: ( RULE_DOUBLE )
            {
            // InternalSTL.g:930:2: ( RULE_DOUBLE )
            // InternalSTL.g:931:3: RULE_DOUBLE
            {
             before(grammarAccess.getVectorAccess().getXDOUBLETerminalRuleCall_0_0()); 
            match(input,RULE_DOUBLE,FOLLOW_2); 
             after(grammarAccess.getVectorAccess().getXDOUBLETerminalRuleCall_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Vector__XAssignment_0"


    // $ANTLR start "rule__Vector__YAssignment_1"
    // InternalSTL.g:940:1: rule__Vector__YAssignment_1 : ( RULE_DOUBLE ) ;
    public final void rule__Vector__YAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:944:1: ( ( RULE_DOUBLE ) )
            // InternalSTL.g:945:2: ( RULE_DOUBLE )
            {
            // InternalSTL.g:945:2: ( RULE_DOUBLE )
            // InternalSTL.g:946:3: RULE_DOUBLE
            {
             before(grammarAccess.getVectorAccess().getYDOUBLETerminalRuleCall_1_0()); 
            match(input,RULE_DOUBLE,FOLLOW_2); 
             after(grammarAccess.getVectorAccess().getYDOUBLETerminalRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Vector__YAssignment_1"


    // $ANTLR start "rule__Vector__ZAssignment_2"
    // InternalSTL.g:955:1: rule__Vector__ZAssignment_2 : ( RULE_DOUBLE ) ;
    public final void rule__Vector__ZAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:959:1: ( ( RULE_DOUBLE ) )
            // InternalSTL.g:960:2: ( RULE_DOUBLE )
            {
            // InternalSTL.g:960:2: ( RULE_DOUBLE )
            // InternalSTL.g:961:3: RULE_DOUBLE
            {
             before(grammarAccess.getVectorAccess().getZDOUBLETerminalRuleCall_2_0()); 
            match(input,RULE_DOUBLE,FOLLOW_2); 
             after(grammarAccess.getVectorAccess().getZDOUBLETerminalRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Vector__ZAssignment_2"


    // $ANTLR start "rule__Verticies__V1Assignment_2"
    // InternalSTL.g:970:1: rule__Verticies__V1Assignment_2 : ( ruleVertex ) ;
    public final void rule__Verticies__V1Assignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:974:1: ( ( ruleVertex ) )
            // InternalSTL.g:975:2: ( ruleVertex )
            {
            // InternalSTL.g:975:2: ( ruleVertex )
            // InternalSTL.g:976:3: ruleVertex
            {
             before(grammarAccess.getVerticiesAccess().getV1VertexParserRuleCall_2_0()); 
            pushFollow(FOLLOW_2);
            ruleVertex();

            state._fsp--;

             after(grammarAccess.getVerticiesAccess().getV1VertexParserRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Verticies__V1Assignment_2"


    // $ANTLR start "rule__Verticies__V2Assignment_3"
    // InternalSTL.g:985:1: rule__Verticies__V2Assignment_3 : ( ruleVertex ) ;
    public final void rule__Verticies__V2Assignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:989:1: ( ( ruleVertex ) )
            // InternalSTL.g:990:2: ( ruleVertex )
            {
            // InternalSTL.g:990:2: ( ruleVertex )
            // InternalSTL.g:991:3: ruleVertex
            {
             before(grammarAccess.getVerticiesAccess().getV2VertexParserRuleCall_3_0()); 
            pushFollow(FOLLOW_2);
            ruleVertex();

            state._fsp--;

             after(grammarAccess.getVerticiesAccess().getV2VertexParserRuleCall_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Verticies__V2Assignment_3"


    // $ANTLR start "rule__Verticies__V3Assignment_4"
    // InternalSTL.g:1000:1: rule__Verticies__V3Assignment_4 : ( ruleVertex ) ;
    public final void rule__Verticies__V3Assignment_4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:1004:1: ( ( ruleVertex ) )
            // InternalSTL.g:1005:2: ( ruleVertex )
            {
            // InternalSTL.g:1005:2: ( ruleVertex )
            // InternalSTL.g:1006:3: ruleVertex
            {
             before(grammarAccess.getVerticiesAccess().getV3VertexParserRuleCall_4_0()); 
            pushFollow(FOLLOW_2);
            ruleVertex();

            state._fsp--;

             after(grammarAccess.getVerticiesAccess().getV3VertexParserRuleCall_4_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Verticies__V3Assignment_4"


    // $ANTLR start "rule__Vertex__XAssignment_1"
    // InternalSTL.g:1015:1: rule__Vertex__XAssignment_1 : ( RULE_DOUBLE ) ;
    public final void rule__Vertex__XAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:1019:1: ( ( RULE_DOUBLE ) )
            // InternalSTL.g:1020:2: ( RULE_DOUBLE )
            {
            // InternalSTL.g:1020:2: ( RULE_DOUBLE )
            // InternalSTL.g:1021:3: RULE_DOUBLE
            {
             before(grammarAccess.getVertexAccess().getXDOUBLETerminalRuleCall_1_0()); 
            match(input,RULE_DOUBLE,FOLLOW_2); 
             after(grammarAccess.getVertexAccess().getXDOUBLETerminalRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Vertex__XAssignment_1"


    // $ANTLR start "rule__Vertex__YAssignment_2"
    // InternalSTL.g:1030:1: rule__Vertex__YAssignment_2 : ( RULE_DOUBLE ) ;
    public final void rule__Vertex__YAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:1034:1: ( ( RULE_DOUBLE ) )
            // InternalSTL.g:1035:2: ( RULE_DOUBLE )
            {
            // InternalSTL.g:1035:2: ( RULE_DOUBLE )
            // InternalSTL.g:1036:3: RULE_DOUBLE
            {
             before(grammarAccess.getVertexAccess().getYDOUBLETerminalRuleCall_2_0()); 
            match(input,RULE_DOUBLE,FOLLOW_2); 
             after(grammarAccess.getVertexAccess().getYDOUBLETerminalRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Vertex__YAssignment_2"


    // $ANTLR start "rule__Vertex__ZAssignment_3"
    // InternalSTL.g:1045:1: rule__Vertex__ZAssignment_3 : ( RULE_DOUBLE ) ;
    public final void rule__Vertex__ZAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:1049:1: ( ( RULE_DOUBLE ) )
            // InternalSTL.g:1050:2: ( RULE_DOUBLE )
            {
            // InternalSTL.g:1050:2: ( RULE_DOUBLE )
            // InternalSTL.g:1051:3: RULE_DOUBLE
            {
             before(grammarAccess.getVertexAccess().getZDOUBLETerminalRuleCall_3_0()); 
            match(input,RULE_DOUBLE,FOLLOW_2); 
             after(grammarAccess.getVertexAccess().getZDOUBLETerminalRuleCall_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Vertex__ZAssignment_3"

    // Delegated rules


 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0000000000006010L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000000000004002L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0000000000080000L});

}