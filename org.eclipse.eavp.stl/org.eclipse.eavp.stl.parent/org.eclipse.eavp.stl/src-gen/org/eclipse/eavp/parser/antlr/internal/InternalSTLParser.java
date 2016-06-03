package org.eclipse.eavp.parser.antlr.internal;

import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import org.eclipse.eavp.services.STLGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalSTLParser extends AbstractInternalAntlrParser {
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

        public InternalSTLParser(TokenStream input, STLGrammarAccess grammarAccess) {
            this(input);
            this.grammarAccess = grammarAccess;
            registerRules(grammarAccess.getGrammar());
        }

        @Override
        protected String getFirstRuleName() {
        	return "Model";
       	}

       	@Override
       	protected STLGrammarAccess getGrammarAccess() {
       		return grammarAccess;
       	}




    // $ANTLR start "entryRuleModel"
    // InternalSTL.g:64:1: entryRuleModel returns [EObject current=null] : iv_ruleModel= ruleModel EOF ;
    public final EObject entryRuleModel() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleModel = null;


        try {
            // InternalSTL.g:64:46: (iv_ruleModel= ruleModel EOF )
            // InternalSTL.g:65:2: iv_ruleModel= ruleModel EOF
            {
             newCompositeNode(grammarAccess.getModelRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleModel=ruleModel();

            state._fsp--;

             current =iv_ruleModel; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleModel"


    // $ANTLR start "ruleModel"
    // InternalSTL.g:71:1: ruleModel returns [EObject current=null] : (otherlv_0= 'solid' ( (lv_name_1_0= RULE_ID ) )? ( (lv_triangles_2_0= ruleTriangle ) )* otherlv_3= 'endsolid' this_ID_4= RULE_ID ) ;
    public final EObject ruleModel() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_name_1_0=null;
        Token otherlv_3=null;
        Token this_ID_4=null;
        EObject lv_triangles_2_0 = null;



        	enterRule();

        try {
            // InternalSTL.g:77:2: ( (otherlv_0= 'solid' ( (lv_name_1_0= RULE_ID ) )? ( (lv_triangles_2_0= ruleTriangle ) )* otherlv_3= 'endsolid' this_ID_4= RULE_ID ) )
            // InternalSTL.g:78:2: (otherlv_0= 'solid' ( (lv_name_1_0= RULE_ID ) )? ( (lv_triangles_2_0= ruleTriangle ) )* otherlv_3= 'endsolid' this_ID_4= RULE_ID )
            {
            // InternalSTL.g:78:2: (otherlv_0= 'solid' ( (lv_name_1_0= RULE_ID ) )? ( (lv_triangles_2_0= ruleTriangle ) )* otherlv_3= 'endsolid' this_ID_4= RULE_ID )
            // InternalSTL.g:79:3: otherlv_0= 'solid' ( (lv_name_1_0= RULE_ID ) )? ( (lv_triangles_2_0= ruleTriangle ) )* otherlv_3= 'endsolid' this_ID_4= RULE_ID
            {
            otherlv_0=(Token)match(input,12,FOLLOW_3); 

            			newLeafNode(otherlv_0, grammarAccess.getModelAccess().getSolidKeyword_0());
            		
            // InternalSTL.g:83:3: ( (lv_name_1_0= RULE_ID ) )?
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==RULE_ID) ) {
                alt1=1;
            }
            switch (alt1) {
                case 1 :
                    // InternalSTL.g:84:4: (lv_name_1_0= RULE_ID )
                    {
                    // InternalSTL.g:84:4: (lv_name_1_0= RULE_ID )
                    // InternalSTL.g:85:5: lv_name_1_0= RULE_ID
                    {
                    lv_name_1_0=(Token)match(input,RULE_ID,FOLLOW_4); 

                    					newLeafNode(lv_name_1_0, grammarAccess.getModelAccess().getNameIDTerminalRuleCall_1_0());
                    				

                    					if (current==null) {
                    						current = createModelElement(grammarAccess.getModelRule());
                    					}
                    					setWithLastConsumed(
                    						current,
                    						"name",
                    						lv_name_1_0,
                    						"org.eclipse.xtext.common.Terminals.ID");
                    				

                    }


                    }
                    break;

            }

            // InternalSTL.g:101:3: ( (lv_triangles_2_0= ruleTriangle ) )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==14) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // InternalSTL.g:102:4: (lv_triangles_2_0= ruleTriangle )
            	    {
            	    // InternalSTL.g:102:4: (lv_triangles_2_0= ruleTriangle )
            	    // InternalSTL.g:103:5: lv_triangles_2_0= ruleTriangle
            	    {

            	    					newCompositeNode(grammarAccess.getModelAccess().getTrianglesTriangleParserRuleCall_2_0());
            	    				
            	    pushFollow(FOLLOW_4);
            	    lv_triangles_2_0=ruleTriangle();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getModelRule());
            	    					}
            	    					add(
            	    						current,
            	    						"triangles",
            	    						lv_triangles_2_0,
            	    						"org.eclipse.eavp.STL.Triangle");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);

            otherlv_3=(Token)match(input,13,FOLLOW_5); 

            			newLeafNode(otherlv_3, grammarAccess.getModelAccess().getEndsolidKeyword_3());
            		
            this_ID_4=(Token)match(input,RULE_ID,FOLLOW_2); 

            			newLeafNode(this_ID_4, grammarAccess.getModelAccess().getIDTerminalRuleCall_4());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleModel"


    // $ANTLR start "entryRuleTriangle"
    // InternalSTL.g:132:1: entryRuleTriangle returns [EObject current=null] : iv_ruleTriangle= ruleTriangle EOF ;
    public final EObject entryRuleTriangle() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTriangle = null;


        try {
            // InternalSTL.g:132:49: (iv_ruleTriangle= ruleTriangle EOF )
            // InternalSTL.g:133:2: iv_ruleTriangle= ruleTriangle EOF
            {
             newCompositeNode(grammarAccess.getTriangleRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleTriangle=ruleTriangle();

            state._fsp--;

             current =iv_ruleTriangle; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleTriangle"


    // $ANTLR start "ruleTriangle"
    // InternalSTL.g:139:1: ruleTriangle returns [EObject current=null] : (otherlv_0= 'facet' ( (lv_normal_1_0= ruleNormal ) ) ( (lv_verticies_2_0= ruleVerticies ) ) otherlv_3= 'endfacet' ) ;
    public final EObject ruleTriangle() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_3=null;
        EObject lv_normal_1_0 = null;

        EObject lv_verticies_2_0 = null;



        	enterRule();

        try {
            // InternalSTL.g:145:2: ( (otherlv_0= 'facet' ( (lv_normal_1_0= ruleNormal ) ) ( (lv_verticies_2_0= ruleVerticies ) ) otherlv_3= 'endfacet' ) )
            // InternalSTL.g:146:2: (otherlv_0= 'facet' ( (lv_normal_1_0= ruleNormal ) ) ( (lv_verticies_2_0= ruleVerticies ) ) otherlv_3= 'endfacet' )
            {
            // InternalSTL.g:146:2: (otherlv_0= 'facet' ( (lv_normal_1_0= ruleNormal ) ) ( (lv_verticies_2_0= ruleVerticies ) ) otherlv_3= 'endfacet' )
            // InternalSTL.g:147:3: otherlv_0= 'facet' ( (lv_normal_1_0= ruleNormal ) ) ( (lv_verticies_2_0= ruleVerticies ) ) otherlv_3= 'endfacet'
            {
            otherlv_0=(Token)match(input,14,FOLLOW_6); 

            			newLeafNode(otherlv_0, grammarAccess.getTriangleAccess().getFacetKeyword_0());
            		
            // InternalSTL.g:151:3: ( (lv_normal_1_0= ruleNormal ) )
            // InternalSTL.g:152:4: (lv_normal_1_0= ruleNormal )
            {
            // InternalSTL.g:152:4: (lv_normal_1_0= ruleNormal )
            // InternalSTL.g:153:5: lv_normal_1_0= ruleNormal
            {

            					newCompositeNode(grammarAccess.getTriangleAccess().getNormalNormalParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_7);
            lv_normal_1_0=ruleNormal();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getTriangleRule());
            					}
            					set(
            						current,
            						"normal",
            						lv_normal_1_0,
            						"org.eclipse.eavp.STL.Normal");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalSTL.g:170:3: ( (lv_verticies_2_0= ruleVerticies ) )
            // InternalSTL.g:171:4: (lv_verticies_2_0= ruleVerticies )
            {
            // InternalSTL.g:171:4: (lv_verticies_2_0= ruleVerticies )
            // InternalSTL.g:172:5: lv_verticies_2_0= ruleVerticies
            {

            					newCompositeNode(grammarAccess.getTriangleAccess().getVerticiesVerticiesParserRuleCall_2_0());
            				
            pushFollow(FOLLOW_8);
            lv_verticies_2_0=ruleVerticies();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getTriangleRule());
            					}
            					set(
            						current,
            						"verticies",
            						lv_verticies_2_0,
            						"org.eclipse.eavp.STL.Verticies");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_3=(Token)match(input,15,FOLLOW_2); 

            			newLeafNode(otherlv_3, grammarAccess.getTriangleAccess().getEndfacetKeyword_3());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleTriangle"


    // $ANTLR start "entryRuleNormal"
    // InternalSTL.g:197:1: entryRuleNormal returns [EObject current=null] : iv_ruleNormal= ruleNormal EOF ;
    public final EObject entryRuleNormal() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNormal = null;


        try {
            // InternalSTL.g:197:47: (iv_ruleNormal= ruleNormal EOF )
            // InternalSTL.g:198:2: iv_ruleNormal= ruleNormal EOF
            {
             newCompositeNode(grammarAccess.getNormalRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleNormal=ruleNormal();

            state._fsp--;

             current =iv_ruleNormal; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleNormal"


    // $ANTLR start "ruleNormal"
    // InternalSTL.g:204:1: ruleNormal returns [EObject current=null] : (otherlv_0= 'normal' ( (lv_vector_1_0= ruleVector ) ) ) ;
    public final EObject ruleNormal() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        EObject lv_vector_1_0 = null;



        	enterRule();

        try {
            // InternalSTL.g:210:2: ( (otherlv_0= 'normal' ( (lv_vector_1_0= ruleVector ) ) ) )
            // InternalSTL.g:211:2: (otherlv_0= 'normal' ( (lv_vector_1_0= ruleVector ) ) )
            {
            // InternalSTL.g:211:2: (otherlv_0= 'normal' ( (lv_vector_1_0= ruleVector ) ) )
            // InternalSTL.g:212:3: otherlv_0= 'normal' ( (lv_vector_1_0= ruleVector ) )
            {
            otherlv_0=(Token)match(input,16,FOLLOW_9); 

            			newLeafNode(otherlv_0, grammarAccess.getNormalAccess().getNormalKeyword_0());
            		
            // InternalSTL.g:216:3: ( (lv_vector_1_0= ruleVector ) )
            // InternalSTL.g:217:4: (lv_vector_1_0= ruleVector )
            {
            // InternalSTL.g:217:4: (lv_vector_1_0= ruleVector )
            // InternalSTL.g:218:5: lv_vector_1_0= ruleVector
            {

            					newCompositeNode(grammarAccess.getNormalAccess().getVectorVectorParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_2);
            lv_vector_1_0=ruleVector();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getNormalRule());
            					}
            					set(
            						current,
            						"vector",
            						lv_vector_1_0,
            						"org.eclipse.eavp.STL.Vector");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleNormal"


    // $ANTLR start "entryRuleVector"
    // InternalSTL.g:239:1: entryRuleVector returns [EObject current=null] : iv_ruleVector= ruleVector EOF ;
    public final EObject entryRuleVector() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleVector = null;


        try {
            // InternalSTL.g:239:47: (iv_ruleVector= ruleVector EOF )
            // InternalSTL.g:240:2: iv_ruleVector= ruleVector EOF
            {
             newCompositeNode(grammarAccess.getVectorRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleVector=ruleVector();

            state._fsp--;

             current =iv_ruleVector; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleVector"


    // $ANTLR start "ruleVector"
    // InternalSTL.g:246:1: ruleVector returns [EObject current=null] : ( ( (lv_x_0_0= RULE_DOUBLE ) ) ( (lv_y_1_0= RULE_DOUBLE ) ) ( (lv_z_2_0= RULE_DOUBLE ) ) ) ;
    public final EObject ruleVector() throws RecognitionException {
        EObject current = null;

        Token lv_x_0_0=null;
        Token lv_y_1_0=null;
        Token lv_z_2_0=null;


        	enterRule();

        try {
            // InternalSTL.g:252:2: ( ( ( (lv_x_0_0= RULE_DOUBLE ) ) ( (lv_y_1_0= RULE_DOUBLE ) ) ( (lv_z_2_0= RULE_DOUBLE ) ) ) )
            // InternalSTL.g:253:2: ( ( (lv_x_0_0= RULE_DOUBLE ) ) ( (lv_y_1_0= RULE_DOUBLE ) ) ( (lv_z_2_0= RULE_DOUBLE ) ) )
            {
            // InternalSTL.g:253:2: ( ( (lv_x_0_0= RULE_DOUBLE ) ) ( (lv_y_1_0= RULE_DOUBLE ) ) ( (lv_z_2_0= RULE_DOUBLE ) ) )
            // InternalSTL.g:254:3: ( (lv_x_0_0= RULE_DOUBLE ) ) ( (lv_y_1_0= RULE_DOUBLE ) ) ( (lv_z_2_0= RULE_DOUBLE ) )
            {
            // InternalSTL.g:254:3: ( (lv_x_0_0= RULE_DOUBLE ) )
            // InternalSTL.g:255:4: (lv_x_0_0= RULE_DOUBLE )
            {
            // InternalSTL.g:255:4: (lv_x_0_0= RULE_DOUBLE )
            // InternalSTL.g:256:5: lv_x_0_0= RULE_DOUBLE
            {
            lv_x_0_0=(Token)match(input,RULE_DOUBLE,FOLLOW_9); 

            					newLeafNode(lv_x_0_0, grammarAccess.getVectorAccess().getXDOUBLETerminalRuleCall_0_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getVectorRule());
            					}
            					setWithLastConsumed(
            						current,
            						"x",
            						lv_x_0_0,
            						"org.eclipse.eavp.STL.DOUBLE");
            				

            }


            }

            // InternalSTL.g:272:3: ( (lv_y_1_0= RULE_DOUBLE ) )
            // InternalSTL.g:273:4: (lv_y_1_0= RULE_DOUBLE )
            {
            // InternalSTL.g:273:4: (lv_y_1_0= RULE_DOUBLE )
            // InternalSTL.g:274:5: lv_y_1_0= RULE_DOUBLE
            {
            lv_y_1_0=(Token)match(input,RULE_DOUBLE,FOLLOW_9); 

            					newLeafNode(lv_y_1_0, grammarAccess.getVectorAccess().getYDOUBLETerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getVectorRule());
            					}
            					setWithLastConsumed(
            						current,
            						"y",
            						lv_y_1_0,
            						"org.eclipse.eavp.STL.DOUBLE");
            				

            }


            }

            // InternalSTL.g:290:3: ( (lv_z_2_0= RULE_DOUBLE ) )
            // InternalSTL.g:291:4: (lv_z_2_0= RULE_DOUBLE )
            {
            // InternalSTL.g:291:4: (lv_z_2_0= RULE_DOUBLE )
            // InternalSTL.g:292:5: lv_z_2_0= RULE_DOUBLE
            {
            lv_z_2_0=(Token)match(input,RULE_DOUBLE,FOLLOW_2); 

            					newLeafNode(lv_z_2_0, grammarAccess.getVectorAccess().getZDOUBLETerminalRuleCall_2_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getVectorRule());
            					}
            					setWithLastConsumed(
            						current,
            						"z",
            						lv_z_2_0,
            						"org.eclipse.eavp.STL.DOUBLE");
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleVector"


    // $ANTLR start "entryRuleVerticies"
    // InternalSTL.g:312:1: entryRuleVerticies returns [EObject current=null] : iv_ruleVerticies= ruleVerticies EOF ;
    public final EObject entryRuleVerticies() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleVerticies = null;


        try {
            // InternalSTL.g:312:50: (iv_ruleVerticies= ruleVerticies EOF )
            // InternalSTL.g:313:2: iv_ruleVerticies= ruleVerticies EOF
            {
             newCompositeNode(grammarAccess.getVerticiesRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleVerticies=ruleVerticies();

            state._fsp--;

             current =iv_ruleVerticies; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleVerticies"


    // $ANTLR start "ruleVerticies"
    // InternalSTL.g:319:1: ruleVerticies returns [EObject current=null] : (otherlv_0= 'outer' otherlv_1= 'loop' ( (lv_v1_2_0= ruleVertex ) ) ( (lv_v2_3_0= ruleVertex ) ) ( (lv_v3_4_0= ruleVertex ) ) otherlv_5= 'endloop' ) ;
    public final EObject ruleVerticies() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_5=null;
        EObject lv_v1_2_0 = null;

        EObject lv_v2_3_0 = null;

        EObject lv_v3_4_0 = null;



        	enterRule();

        try {
            // InternalSTL.g:325:2: ( (otherlv_0= 'outer' otherlv_1= 'loop' ( (lv_v1_2_0= ruleVertex ) ) ( (lv_v2_3_0= ruleVertex ) ) ( (lv_v3_4_0= ruleVertex ) ) otherlv_5= 'endloop' ) )
            // InternalSTL.g:326:2: (otherlv_0= 'outer' otherlv_1= 'loop' ( (lv_v1_2_0= ruleVertex ) ) ( (lv_v2_3_0= ruleVertex ) ) ( (lv_v3_4_0= ruleVertex ) ) otherlv_5= 'endloop' )
            {
            // InternalSTL.g:326:2: (otherlv_0= 'outer' otherlv_1= 'loop' ( (lv_v1_2_0= ruleVertex ) ) ( (lv_v2_3_0= ruleVertex ) ) ( (lv_v3_4_0= ruleVertex ) ) otherlv_5= 'endloop' )
            // InternalSTL.g:327:3: otherlv_0= 'outer' otherlv_1= 'loop' ( (lv_v1_2_0= ruleVertex ) ) ( (lv_v2_3_0= ruleVertex ) ) ( (lv_v3_4_0= ruleVertex ) ) otherlv_5= 'endloop'
            {
            otherlv_0=(Token)match(input,17,FOLLOW_10); 

            			newLeafNode(otherlv_0, grammarAccess.getVerticiesAccess().getOuterKeyword_0());
            		
            otherlv_1=(Token)match(input,18,FOLLOW_11); 

            			newLeafNode(otherlv_1, grammarAccess.getVerticiesAccess().getLoopKeyword_1());
            		
            // InternalSTL.g:335:3: ( (lv_v1_2_0= ruleVertex ) )
            // InternalSTL.g:336:4: (lv_v1_2_0= ruleVertex )
            {
            // InternalSTL.g:336:4: (lv_v1_2_0= ruleVertex )
            // InternalSTL.g:337:5: lv_v1_2_0= ruleVertex
            {

            					newCompositeNode(grammarAccess.getVerticiesAccess().getV1VertexParserRuleCall_2_0());
            				
            pushFollow(FOLLOW_11);
            lv_v1_2_0=ruleVertex();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getVerticiesRule());
            					}
            					set(
            						current,
            						"v1",
            						lv_v1_2_0,
            						"org.eclipse.eavp.STL.Vertex");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalSTL.g:354:3: ( (lv_v2_3_0= ruleVertex ) )
            // InternalSTL.g:355:4: (lv_v2_3_0= ruleVertex )
            {
            // InternalSTL.g:355:4: (lv_v2_3_0= ruleVertex )
            // InternalSTL.g:356:5: lv_v2_3_0= ruleVertex
            {

            					newCompositeNode(grammarAccess.getVerticiesAccess().getV2VertexParserRuleCall_3_0());
            				
            pushFollow(FOLLOW_11);
            lv_v2_3_0=ruleVertex();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getVerticiesRule());
            					}
            					set(
            						current,
            						"v2",
            						lv_v2_3_0,
            						"org.eclipse.eavp.STL.Vertex");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalSTL.g:373:3: ( (lv_v3_4_0= ruleVertex ) )
            // InternalSTL.g:374:4: (lv_v3_4_0= ruleVertex )
            {
            // InternalSTL.g:374:4: (lv_v3_4_0= ruleVertex )
            // InternalSTL.g:375:5: lv_v3_4_0= ruleVertex
            {

            					newCompositeNode(grammarAccess.getVerticiesAccess().getV3VertexParserRuleCall_4_0());
            				
            pushFollow(FOLLOW_12);
            lv_v3_4_0=ruleVertex();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getVerticiesRule());
            					}
            					set(
            						current,
            						"v3",
            						lv_v3_4_0,
            						"org.eclipse.eavp.STL.Vertex");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_5=(Token)match(input,19,FOLLOW_2); 

            			newLeafNode(otherlv_5, grammarAccess.getVerticiesAccess().getEndloopKeyword_5());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleVerticies"


    // $ANTLR start "entryRuleVertex"
    // InternalSTL.g:400:1: entryRuleVertex returns [EObject current=null] : iv_ruleVertex= ruleVertex EOF ;
    public final EObject entryRuleVertex() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleVertex = null;


        try {
            // InternalSTL.g:400:47: (iv_ruleVertex= ruleVertex EOF )
            // InternalSTL.g:401:2: iv_ruleVertex= ruleVertex EOF
            {
             newCompositeNode(grammarAccess.getVertexRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleVertex=ruleVertex();

            state._fsp--;

             current =iv_ruleVertex; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleVertex"


    // $ANTLR start "ruleVertex"
    // InternalSTL.g:407:1: ruleVertex returns [EObject current=null] : (otherlv_0= 'vertex' ( (lv_x_1_0= RULE_DOUBLE ) ) ( (lv_y_2_0= RULE_DOUBLE ) ) ( (lv_z_3_0= RULE_DOUBLE ) ) ) ;
    public final EObject ruleVertex() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_x_1_0=null;
        Token lv_y_2_0=null;
        Token lv_z_3_0=null;


        	enterRule();

        try {
            // InternalSTL.g:413:2: ( (otherlv_0= 'vertex' ( (lv_x_1_0= RULE_DOUBLE ) ) ( (lv_y_2_0= RULE_DOUBLE ) ) ( (lv_z_3_0= RULE_DOUBLE ) ) ) )
            // InternalSTL.g:414:2: (otherlv_0= 'vertex' ( (lv_x_1_0= RULE_DOUBLE ) ) ( (lv_y_2_0= RULE_DOUBLE ) ) ( (lv_z_3_0= RULE_DOUBLE ) ) )
            {
            // InternalSTL.g:414:2: (otherlv_0= 'vertex' ( (lv_x_1_0= RULE_DOUBLE ) ) ( (lv_y_2_0= RULE_DOUBLE ) ) ( (lv_z_3_0= RULE_DOUBLE ) ) )
            // InternalSTL.g:415:3: otherlv_0= 'vertex' ( (lv_x_1_0= RULE_DOUBLE ) ) ( (lv_y_2_0= RULE_DOUBLE ) ) ( (lv_z_3_0= RULE_DOUBLE ) )
            {
            otherlv_0=(Token)match(input,20,FOLLOW_9); 

            			newLeafNode(otherlv_0, grammarAccess.getVertexAccess().getVertexKeyword_0());
            		
            // InternalSTL.g:419:3: ( (lv_x_1_0= RULE_DOUBLE ) )
            // InternalSTL.g:420:4: (lv_x_1_0= RULE_DOUBLE )
            {
            // InternalSTL.g:420:4: (lv_x_1_0= RULE_DOUBLE )
            // InternalSTL.g:421:5: lv_x_1_0= RULE_DOUBLE
            {
            lv_x_1_0=(Token)match(input,RULE_DOUBLE,FOLLOW_9); 

            					newLeafNode(lv_x_1_0, grammarAccess.getVertexAccess().getXDOUBLETerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getVertexRule());
            					}
            					setWithLastConsumed(
            						current,
            						"x",
            						lv_x_1_0,
            						"org.eclipse.eavp.STL.DOUBLE");
            				

            }


            }

            // InternalSTL.g:437:3: ( (lv_y_2_0= RULE_DOUBLE ) )
            // InternalSTL.g:438:4: (lv_y_2_0= RULE_DOUBLE )
            {
            // InternalSTL.g:438:4: (lv_y_2_0= RULE_DOUBLE )
            // InternalSTL.g:439:5: lv_y_2_0= RULE_DOUBLE
            {
            lv_y_2_0=(Token)match(input,RULE_DOUBLE,FOLLOW_9); 

            					newLeafNode(lv_y_2_0, grammarAccess.getVertexAccess().getYDOUBLETerminalRuleCall_2_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getVertexRule());
            					}
            					setWithLastConsumed(
            						current,
            						"y",
            						lv_y_2_0,
            						"org.eclipse.eavp.STL.DOUBLE");
            				

            }


            }

            // InternalSTL.g:455:3: ( (lv_z_3_0= RULE_DOUBLE ) )
            // InternalSTL.g:456:4: (lv_z_3_0= RULE_DOUBLE )
            {
            // InternalSTL.g:456:4: (lv_z_3_0= RULE_DOUBLE )
            // InternalSTL.g:457:5: lv_z_3_0= RULE_DOUBLE
            {
            lv_z_3_0=(Token)match(input,RULE_DOUBLE,FOLLOW_2); 

            					newLeafNode(lv_z_3_0, grammarAccess.getVertexAccess().getZDOUBLETerminalRuleCall_3_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getVertexRule());
            					}
            					setWithLastConsumed(
            						current,
            						"z",
            						lv_z_3_0,
            						"org.eclipse.eavp.STL.DOUBLE");
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleVertex"

    // Delegated rules


 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0000000000006010L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000000000006000L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0000000000080000L});

}