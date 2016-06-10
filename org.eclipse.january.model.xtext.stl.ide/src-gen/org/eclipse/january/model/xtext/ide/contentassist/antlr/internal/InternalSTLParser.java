package org.eclipse.january.model.xtext.ide.contentassist.antlr.internal;

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
import org.eclipse.january.model.xtext.services.STLGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalSTLParser extends AbstractInternalContentAssistParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_STRING", "RULE_ID", "RULE_INT", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'-'", "'+'", "'E'", "'e'", "'solid'", "'endsolid'", "'facet'", "'outer'", "'loop'", "'endloop'", "'endfacet'", "'normal'", "'vertex'", "'.'"
    };
    public static final int RULE_STRING=4;
    public static final int RULE_SL_COMMENT=8;
    public static final int T__19=19;
    public static final int T__15=15;
    public static final int T__16=16;
    public static final int T__17=17;
    public static final int T__18=18;
    public static final int T__11=11;
    public static final int T__12=12;
    public static final int T__13=13;
    public static final int T__14=14;
    public static final int EOF=-1;
    public static final int RULE_ID=5;
    public static final int RULE_WS=9;
    public static final int RULE_ANY_OTHER=10;
    public static final int RULE_INT=6;
    public static final int T__22=22;
    public static final int RULE_ML_COMMENT=7;
    public static final int T__23=23;
    public static final int T__24=24;
    public static final int T__20=20;
    public static final int T__21=21;

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



    // $ANTLR start "entryRuleGeometry"
    // InternalSTL.g:53:1: entryRuleGeometry : ruleGeometry EOF ;
    public final void entryRuleGeometry() throws RecognitionException {
        try {
            // InternalSTL.g:54:1: ( ruleGeometry EOF )
            // InternalSTL.g:55:1: ruleGeometry EOF
            {
             before(grammarAccess.getGeometryRule()); 
            pushFollow(FOLLOW_1);
            ruleGeometry();

            state._fsp--;

             after(grammarAccess.getGeometryRule()); 
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
    // $ANTLR end "entryRuleGeometry"


    // $ANTLR start "ruleGeometry"
    // InternalSTL.g:62:1: ruleGeometry : ( ( rule__Geometry__Group__0 ) ) ;
    public final void ruleGeometry() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:66:2: ( ( ( rule__Geometry__Group__0 ) ) )
            // InternalSTL.g:67:2: ( ( rule__Geometry__Group__0 ) )
            {
            // InternalSTL.g:67:2: ( ( rule__Geometry__Group__0 ) )
            // InternalSTL.g:68:3: ( rule__Geometry__Group__0 )
            {
             before(grammarAccess.getGeometryAccess().getGroup()); 
            // InternalSTL.g:69:3: ( rule__Geometry__Group__0 )
            // InternalSTL.g:69:4: rule__Geometry__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Geometry__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getGeometryAccess().getGroup()); 

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
    // $ANTLR end "ruleGeometry"


    // $ANTLR start "entryRuleShape_Impl"
    // InternalSTL.g:78:1: entryRuleShape_Impl : ruleShape_Impl EOF ;
    public final void entryRuleShape_Impl() throws RecognitionException {
        try {
            // InternalSTL.g:79:1: ( ruleShape_Impl EOF )
            // InternalSTL.g:80:1: ruleShape_Impl EOF
            {
             before(grammarAccess.getShape_ImplRule()); 
            pushFollow(FOLLOW_1);
            ruleShape_Impl();

            state._fsp--;

             after(grammarAccess.getShape_ImplRule()); 
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
    // $ANTLR end "entryRuleShape_Impl"


    // $ANTLR start "ruleShape_Impl"
    // InternalSTL.g:87:1: ruleShape_Impl : ( ( rule__Shape_Impl__Group__0 ) ) ;
    public final void ruleShape_Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:91:2: ( ( ( rule__Shape_Impl__Group__0 ) ) )
            // InternalSTL.g:92:2: ( ( rule__Shape_Impl__Group__0 ) )
            {
            // InternalSTL.g:92:2: ( ( rule__Shape_Impl__Group__0 ) )
            // InternalSTL.g:93:3: ( rule__Shape_Impl__Group__0 )
            {
             before(grammarAccess.getShape_ImplAccess().getGroup()); 
            // InternalSTL.g:94:3: ( rule__Shape_Impl__Group__0 )
            // InternalSTL.g:94:4: rule__Shape_Impl__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Shape_Impl__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getShape_ImplAccess().getGroup()); 

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
    // $ANTLR end "ruleShape_Impl"


    // $ANTLR start "entryRuleTriangle"
    // InternalSTL.g:103:1: entryRuleTriangle : ruleTriangle EOF ;
    public final void entryRuleTriangle() throws RecognitionException {
        try {
            // InternalSTL.g:104:1: ( ruleTriangle EOF )
            // InternalSTL.g:105:1: ruleTriangle EOF
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
    // InternalSTL.g:112:1: ruleTriangle : ( ( rule__Triangle__Group__0 ) ) ;
    public final void ruleTriangle() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:116:2: ( ( ( rule__Triangle__Group__0 ) ) )
            // InternalSTL.g:117:2: ( ( rule__Triangle__Group__0 ) )
            {
            // InternalSTL.g:117:2: ( ( rule__Triangle__Group__0 ) )
            // InternalSTL.g:118:3: ( rule__Triangle__Group__0 )
            {
             before(grammarAccess.getTriangleAccess().getGroup()); 
            // InternalSTL.g:119:3: ( rule__Triangle__Group__0 )
            // InternalSTL.g:119:4: rule__Triangle__Group__0
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


    // $ANTLR start "entryRuleVertex"
    // InternalSTL.g:128:1: entryRuleVertex : ruleVertex EOF ;
    public final void entryRuleVertex() throws RecognitionException {
        try {
            // InternalSTL.g:129:1: ( ruleVertex EOF )
            // InternalSTL.g:130:1: ruleVertex EOF
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
    // InternalSTL.g:137:1: ruleVertex : ( ( rule__Vertex__Group__0 ) ) ;
    public final void ruleVertex() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:141:2: ( ( ( rule__Vertex__Group__0 ) ) )
            // InternalSTL.g:142:2: ( ( rule__Vertex__Group__0 ) )
            {
            // InternalSTL.g:142:2: ( ( rule__Vertex__Group__0 ) )
            // InternalSTL.g:143:3: ( rule__Vertex__Group__0 )
            {
             before(grammarAccess.getVertexAccess().getGroup()); 
            // InternalSTL.g:144:3: ( rule__Vertex__Group__0 )
            // InternalSTL.g:144:4: rule__Vertex__Group__0
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


    // $ANTLR start "entryRuleEDouble"
    // InternalSTL.g:153:1: entryRuleEDouble : ruleEDouble EOF ;
    public final void entryRuleEDouble() throws RecognitionException {
        try {
            // InternalSTL.g:154:1: ( ruleEDouble EOF )
            // InternalSTL.g:155:1: ruleEDouble EOF
            {
             before(grammarAccess.getEDoubleRule()); 
            pushFollow(FOLLOW_1);
            ruleEDouble();

            state._fsp--;

             after(grammarAccess.getEDoubleRule()); 
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
    // $ANTLR end "entryRuleEDouble"


    // $ANTLR start "ruleEDouble"
    // InternalSTL.g:162:1: ruleEDouble : ( ( rule__EDouble__Group__0 ) ) ;
    public final void ruleEDouble() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:166:2: ( ( ( rule__EDouble__Group__0 ) ) )
            // InternalSTL.g:167:2: ( ( rule__EDouble__Group__0 ) )
            {
            // InternalSTL.g:167:2: ( ( rule__EDouble__Group__0 ) )
            // InternalSTL.g:168:3: ( rule__EDouble__Group__0 )
            {
             before(grammarAccess.getEDoubleAccess().getGroup()); 
            // InternalSTL.g:169:3: ( rule__EDouble__Group__0 )
            // InternalSTL.g:169:4: rule__EDouble__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__EDouble__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getEDoubleAccess().getGroup()); 

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
    // $ANTLR end "ruleEDouble"


    // $ANTLR start "entryRuleEString"
    // InternalSTL.g:178:1: entryRuleEString : ruleEString EOF ;
    public final void entryRuleEString() throws RecognitionException {
        try {
            // InternalSTL.g:179:1: ( ruleEString EOF )
            // InternalSTL.g:180:1: ruleEString EOF
            {
             before(grammarAccess.getEStringRule()); 
            pushFollow(FOLLOW_1);
            ruleEString();

            state._fsp--;

             after(grammarAccess.getEStringRule()); 
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
    // $ANTLR end "entryRuleEString"


    // $ANTLR start "ruleEString"
    // InternalSTL.g:187:1: ruleEString : ( ( rule__EString__Alternatives ) ) ;
    public final void ruleEString() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:191:2: ( ( ( rule__EString__Alternatives ) ) )
            // InternalSTL.g:192:2: ( ( rule__EString__Alternatives ) )
            {
            // InternalSTL.g:192:2: ( ( rule__EString__Alternatives ) )
            // InternalSTL.g:193:3: ( rule__EString__Alternatives )
            {
             before(grammarAccess.getEStringAccess().getAlternatives()); 
            // InternalSTL.g:194:3: ( rule__EString__Alternatives )
            // InternalSTL.g:194:4: rule__EString__Alternatives
            {
            pushFollow(FOLLOW_2);
            rule__EString__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getEStringAccess().getAlternatives()); 

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
    // $ANTLR end "ruleEString"


    // $ANTLR start "rule__EDouble__Alternatives_0"
    // InternalSTL.g:202:1: rule__EDouble__Alternatives_0 : ( ( '-' ) | ( '+' ) );
    public final void rule__EDouble__Alternatives_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:206:1: ( ( '-' ) | ( '+' ) )
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==11) ) {
                alt1=1;
            }
            else if ( (LA1_0==12) ) {
                alt1=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 1, 0, input);

                throw nvae;
            }
            switch (alt1) {
                case 1 :
                    // InternalSTL.g:207:2: ( '-' )
                    {
                    // InternalSTL.g:207:2: ( '-' )
                    // InternalSTL.g:208:3: '-'
                    {
                     before(grammarAccess.getEDoubleAccess().getHyphenMinusKeyword_0_0()); 
                    match(input,11,FOLLOW_2); 
                     after(grammarAccess.getEDoubleAccess().getHyphenMinusKeyword_0_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalSTL.g:213:2: ( '+' )
                    {
                    // InternalSTL.g:213:2: ( '+' )
                    // InternalSTL.g:214:3: '+'
                    {
                     before(grammarAccess.getEDoubleAccess().getPlusSignKeyword_0_1()); 
                    match(input,12,FOLLOW_2); 
                     after(grammarAccess.getEDoubleAccess().getPlusSignKeyword_0_1()); 

                    }


                    }
                    break;

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
    // $ANTLR end "rule__EDouble__Alternatives_0"


    // $ANTLR start "rule__EDouble__Alternatives_4_0"
    // InternalSTL.g:223:1: rule__EDouble__Alternatives_4_0 : ( ( 'E' ) | ( 'e' ) );
    public final void rule__EDouble__Alternatives_4_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:227:1: ( ( 'E' ) | ( 'e' ) )
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==13) ) {
                alt2=1;
            }
            else if ( (LA2_0==14) ) {
                alt2=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;
            }
            switch (alt2) {
                case 1 :
                    // InternalSTL.g:228:2: ( 'E' )
                    {
                    // InternalSTL.g:228:2: ( 'E' )
                    // InternalSTL.g:229:3: 'E'
                    {
                     before(grammarAccess.getEDoubleAccess().getEKeyword_4_0_0()); 
                    match(input,13,FOLLOW_2); 
                     after(grammarAccess.getEDoubleAccess().getEKeyword_4_0_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalSTL.g:234:2: ( 'e' )
                    {
                    // InternalSTL.g:234:2: ( 'e' )
                    // InternalSTL.g:235:3: 'e'
                    {
                     before(grammarAccess.getEDoubleAccess().getEKeyword_4_0_1()); 
                    match(input,14,FOLLOW_2); 
                     after(grammarAccess.getEDoubleAccess().getEKeyword_4_0_1()); 

                    }


                    }
                    break;

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
    // $ANTLR end "rule__EDouble__Alternatives_4_0"


    // $ANTLR start "rule__EDouble__Alternatives_4_1"
    // InternalSTL.g:244:1: rule__EDouble__Alternatives_4_1 : ( ( '-' ) | ( '+' ) );
    public final void rule__EDouble__Alternatives_4_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:248:1: ( ( '-' ) | ( '+' ) )
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==11) ) {
                alt3=1;
            }
            else if ( (LA3_0==12) ) {
                alt3=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;
            }
            switch (alt3) {
                case 1 :
                    // InternalSTL.g:249:2: ( '-' )
                    {
                    // InternalSTL.g:249:2: ( '-' )
                    // InternalSTL.g:250:3: '-'
                    {
                     before(grammarAccess.getEDoubleAccess().getHyphenMinusKeyword_4_1_0()); 
                    match(input,11,FOLLOW_2); 
                     after(grammarAccess.getEDoubleAccess().getHyphenMinusKeyword_4_1_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalSTL.g:255:2: ( '+' )
                    {
                    // InternalSTL.g:255:2: ( '+' )
                    // InternalSTL.g:256:3: '+'
                    {
                     before(grammarAccess.getEDoubleAccess().getPlusSignKeyword_4_1_1()); 
                    match(input,12,FOLLOW_2); 
                     after(grammarAccess.getEDoubleAccess().getPlusSignKeyword_4_1_1()); 

                    }


                    }
                    break;

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
    // $ANTLR end "rule__EDouble__Alternatives_4_1"


    // $ANTLR start "rule__EString__Alternatives"
    // InternalSTL.g:265:1: rule__EString__Alternatives : ( ( RULE_STRING ) | ( RULE_ID ) );
    public final void rule__EString__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:269:1: ( ( RULE_STRING ) | ( RULE_ID ) )
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==RULE_STRING) ) {
                alt4=1;
            }
            else if ( (LA4_0==RULE_ID) ) {
                alt4=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }
            switch (alt4) {
                case 1 :
                    // InternalSTL.g:270:2: ( RULE_STRING )
                    {
                    // InternalSTL.g:270:2: ( RULE_STRING )
                    // InternalSTL.g:271:3: RULE_STRING
                    {
                     before(grammarAccess.getEStringAccess().getSTRINGTerminalRuleCall_0()); 
                    match(input,RULE_STRING,FOLLOW_2); 
                     after(grammarAccess.getEStringAccess().getSTRINGTerminalRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalSTL.g:276:2: ( RULE_ID )
                    {
                    // InternalSTL.g:276:2: ( RULE_ID )
                    // InternalSTL.g:277:3: RULE_ID
                    {
                     before(grammarAccess.getEStringAccess().getIDTerminalRuleCall_1()); 
                    match(input,RULE_ID,FOLLOW_2); 
                     after(grammarAccess.getEStringAccess().getIDTerminalRuleCall_1()); 

                    }


                    }
                    break;

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
    // $ANTLR end "rule__EString__Alternatives"


    // $ANTLR start "rule__Geometry__Group__0"
    // InternalSTL.g:286:1: rule__Geometry__Group__0 : rule__Geometry__Group__0__Impl rule__Geometry__Group__1 ;
    public final void rule__Geometry__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:290:1: ( rule__Geometry__Group__0__Impl rule__Geometry__Group__1 )
            // InternalSTL.g:291:2: rule__Geometry__Group__0__Impl rule__Geometry__Group__1
            {
            pushFollow(FOLLOW_3);
            rule__Geometry__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Geometry__Group__1();

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
    // $ANTLR end "rule__Geometry__Group__0"


    // $ANTLR start "rule__Geometry__Group__0__Impl"
    // InternalSTL.g:298:1: rule__Geometry__Group__0__Impl : ( () ) ;
    public final void rule__Geometry__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:302:1: ( ( () ) )
            // InternalSTL.g:303:1: ( () )
            {
            // InternalSTL.g:303:1: ( () )
            // InternalSTL.g:304:2: ()
            {
             before(grammarAccess.getGeometryAccess().getGeometryAction_0()); 
            // InternalSTL.g:305:2: ()
            // InternalSTL.g:305:3: 
            {
            }

             after(grammarAccess.getGeometryAccess().getGeometryAction_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Geometry__Group__0__Impl"


    // $ANTLR start "rule__Geometry__Group__1"
    // InternalSTL.g:313:1: rule__Geometry__Group__1 : rule__Geometry__Group__1__Impl rule__Geometry__Group__2 ;
    public final void rule__Geometry__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:317:1: ( rule__Geometry__Group__1__Impl rule__Geometry__Group__2 )
            // InternalSTL.g:318:2: rule__Geometry__Group__1__Impl rule__Geometry__Group__2
            {
            pushFollow(FOLLOW_4);
            rule__Geometry__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Geometry__Group__2();

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
    // $ANTLR end "rule__Geometry__Group__1"


    // $ANTLR start "rule__Geometry__Group__1__Impl"
    // InternalSTL.g:325:1: rule__Geometry__Group__1__Impl : ( 'solid' ) ;
    public final void rule__Geometry__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:329:1: ( ( 'solid' ) )
            // InternalSTL.g:330:1: ( 'solid' )
            {
            // InternalSTL.g:330:1: ( 'solid' )
            // InternalSTL.g:331:2: 'solid'
            {
             before(grammarAccess.getGeometryAccess().getSolidKeyword_1()); 
            match(input,15,FOLLOW_2); 
             after(grammarAccess.getGeometryAccess().getSolidKeyword_1()); 

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
    // $ANTLR end "rule__Geometry__Group__1__Impl"


    // $ANTLR start "rule__Geometry__Group__2"
    // InternalSTL.g:340:1: rule__Geometry__Group__2 : rule__Geometry__Group__2__Impl rule__Geometry__Group__3 ;
    public final void rule__Geometry__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:344:1: ( rule__Geometry__Group__2__Impl rule__Geometry__Group__3 )
            // InternalSTL.g:345:2: rule__Geometry__Group__2__Impl rule__Geometry__Group__3
            {
            pushFollow(FOLLOW_4);
            rule__Geometry__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Geometry__Group__3();

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
    // $ANTLR end "rule__Geometry__Group__2"


    // $ANTLR start "rule__Geometry__Group__2__Impl"
    // InternalSTL.g:352:1: rule__Geometry__Group__2__Impl : ( ( rule__Geometry__NameAssignment_2 )? ) ;
    public final void rule__Geometry__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:356:1: ( ( ( rule__Geometry__NameAssignment_2 )? ) )
            // InternalSTL.g:357:1: ( ( rule__Geometry__NameAssignment_2 )? )
            {
            // InternalSTL.g:357:1: ( ( rule__Geometry__NameAssignment_2 )? )
            // InternalSTL.g:358:2: ( rule__Geometry__NameAssignment_2 )?
            {
             before(grammarAccess.getGeometryAccess().getNameAssignment_2()); 
            // InternalSTL.g:359:2: ( rule__Geometry__NameAssignment_2 )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( ((LA5_0>=RULE_STRING && LA5_0<=RULE_ID)) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // InternalSTL.g:359:3: rule__Geometry__NameAssignment_2
                    {
                    pushFollow(FOLLOW_2);
                    rule__Geometry__NameAssignment_2();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getGeometryAccess().getNameAssignment_2()); 

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
    // $ANTLR end "rule__Geometry__Group__2__Impl"


    // $ANTLR start "rule__Geometry__Group__3"
    // InternalSTL.g:367:1: rule__Geometry__Group__3 : rule__Geometry__Group__3__Impl rule__Geometry__Group__4 ;
    public final void rule__Geometry__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:371:1: ( rule__Geometry__Group__3__Impl rule__Geometry__Group__4 )
            // InternalSTL.g:372:2: rule__Geometry__Group__3__Impl rule__Geometry__Group__4
            {
            pushFollow(FOLLOW_5);
            rule__Geometry__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Geometry__Group__4();

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
    // $ANTLR end "rule__Geometry__Group__3"


    // $ANTLR start "rule__Geometry__Group__3__Impl"
    // InternalSTL.g:379:1: rule__Geometry__Group__3__Impl : ( ( rule__Geometry__NodesAssignment_3 ) ) ;
    public final void rule__Geometry__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:383:1: ( ( ( rule__Geometry__NodesAssignment_3 ) ) )
            // InternalSTL.g:384:1: ( ( rule__Geometry__NodesAssignment_3 ) )
            {
            // InternalSTL.g:384:1: ( ( rule__Geometry__NodesAssignment_3 ) )
            // InternalSTL.g:385:2: ( rule__Geometry__NodesAssignment_3 )
            {
             before(grammarAccess.getGeometryAccess().getNodesAssignment_3()); 
            // InternalSTL.g:386:2: ( rule__Geometry__NodesAssignment_3 )
            // InternalSTL.g:386:3: rule__Geometry__NodesAssignment_3
            {
            pushFollow(FOLLOW_2);
            rule__Geometry__NodesAssignment_3();

            state._fsp--;


            }

             after(grammarAccess.getGeometryAccess().getNodesAssignment_3()); 

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
    // $ANTLR end "rule__Geometry__Group__3__Impl"


    // $ANTLR start "rule__Geometry__Group__4"
    // InternalSTL.g:394:1: rule__Geometry__Group__4 : rule__Geometry__Group__4__Impl ;
    public final void rule__Geometry__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:398:1: ( rule__Geometry__Group__4__Impl )
            // InternalSTL.g:399:2: rule__Geometry__Group__4__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Geometry__Group__4__Impl();

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
    // $ANTLR end "rule__Geometry__Group__4"


    // $ANTLR start "rule__Geometry__Group__4__Impl"
    // InternalSTL.g:405:1: rule__Geometry__Group__4__Impl : ( 'endsolid' ) ;
    public final void rule__Geometry__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:409:1: ( ( 'endsolid' ) )
            // InternalSTL.g:410:1: ( 'endsolid' )
            {
            // InternalSTL.g:410:1: ( 'endsolid' )
            // InternalSTL.g:411:2: 'endsolid'
            {
             before(grammarAccess.getGeometryAccess().getEndsolidKeyword_4()); 
            match(input,16,FOLLOW_2); 
             after(grammarAccess.getGeometryAccess().getEndsolidKeyword_4()); 

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
    // $ANTLR end "rule__Geometry__Group__4__Impl"


    // $ANTLR start "rule__Shape_Impl__Group__0"
    // InternalSTL.g:421:1: rule__Shape_Impl__Group__0 : rule__Shape_Impl__Group__0__Impl rule__Shape_Impl__Group__1 ;
    public final void rule__Shape_Impl__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:425:1: ( rule__Shape_Impl__Group__0__Impl rule__Shape_Impl__Group__1 )
            // InternalSTL.g:426:2: rule__Shape_Impl__Group__0__Impl rule__Shape_Impl__Group__1
            {
            pushFollow(FOLLOW_4);
            rule__Shape_Impl__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Shape_Impl__Group__1();

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
    // $ANTLR end "rule__Shape_Impl__Group__0"


    // $ANTLR start "rule__Shape_Impl__Group__0__Impl"
    // InternalSTL.g:433:1: rule__Shape_Impl__Group__0__Impl : ( () ) ;
    public final void rule__Shape_Impl__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:437:1: ( ( () ) )
            // InternalSTL.g:438:1: ( () )
            {
            // InternalSTL.g:438:1: ( () )
            // InternalSTL.g:439:2: ()
            {
             before(grammarAccess.getShape_ImplAccess().getShapeAction_0()); 
            // InternalSTL.g:440:2: ()
            // InternalSTL.g:440:3: 
            {
            }

             after(grammarAccess.getShape_ImplAccess().getShapeAction_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Shape_Impl__Group__0__Impl"


    // $ANTLR start "rule__Shape_Impl__Group__1"
    // InternalSTL.g:448:1: rule__Shape_Impl__Group__1 : rule__Shape_Impl__Group__1__Impl ;
    public final void rule__Shape_Impl__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:452:1: ( rule__Shape_Impl__Group__1__Impl )
            // InternalSTL.g:453:2: rule__Shape_Impl__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Shape_Impl__Group__1__Impl();

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
    // $ANTLR end "rule__Shape_Impl__Group__1"


    // $ANTLR start "rule__Shape_Impl__Group__1__Impl"
    // InternalSTL.g:459:1: rule__Shape_Impl__Group__1__Impl : ( ( rule__Shape_Impl__TrianglesAssignment_1 )* ) ;
    public final void rule__Shape_Impl__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:463:1: ( ( ( rule__Shape_Impl__TrianglesAssignment_1 )* ) )
            // InternalSTL.g:464:1: ( ( rule__Shape_Impl__TrianglesAssignment_1 )* )
            {
            // InternalSTL.g:464:1: ( ( rule__Shape_Impl__TrianglesAssignment_1 )* )
            // InternalSTL.g:465:2: ( rule__Shape_Impl__TrianglesAssignment_1 )*
            {
             before(grammarAccess.getShape_ImplAccess().getTrianglesAssignment_1()); 
            // InternalSTL.g:466:2: ( rule__Shape_Impl__TrianglesAssignment_1 )*
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( (LA6_0==17) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // InternalSTL.g:466:3: rule__Shape_Impl__TrianglesAssignment_1
            	    {
            	    pushFollow(FOLLOW_6);
            	    rule__Shape_Impl__TrianglesAssignment_1();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop6;
                }
            } while (true);

             after(grammarAccess.getShape_ImplAccess().getTrianglesAssignment_1()); 

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
    // $ANTLR end "rule__Shape_Impl__Group__1__Impl"


    // $ANTLR start "rule__Triangle__Group__0"
    // InternalSTL.g:475:1: rule__Triangle__Group__0 : rule__Triangle__Group__0__Impl rule__Triangle__Group__1 ;
    public final void rule__Triangle__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:479:1: ( rule__Triangle__Group__0__Impl rule__Triangle__Group__1 )
            // InternalSTL.g:480:2: rule__Triangle__Group__0__Impl rule__Triangle__Group__1
            {
            pushFollow(FOLLOW_7);
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
    // InternalSTL.g:487:1: rule__Triangle__Group__0__Impl : ( () ) ;
    public final void rule__Triangle__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:491:1: ( ( () ) )
            // InternalSTL.g:492:1: ( () )
            {
            // InternalSTL.g:492:1: ( () )
            // InternalSTL.g:493:2: ()
            {
             before(grammarAccess.getTriangleAccess().getTriangleAction_0()); 
            // InternalSTL.g:494:2: ()
            // InternalSTL.g:494:3: 
            {
            }

             after(grammarAccess.getTriangleAccess().getTriangleAction_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Triangle__Group__0__Impl"


    // $ANTLR start "rule__Triangle__Group__1"
    // InternalSTL.g:502:1: rule__Triangle__Group__1 : rule__Triangle__Group__1__Impl rule__Triangle__Group__2 ;
    public final void rule__Triangle__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:506:1: ( rule__Triangle__Group__1__Impl rule__Triangle__Group__2 )
            // InternalSTL.g:507:2: rule__Triangle__Group__1__Impl rule__Triangle__Group__2
            {
            pushFollow(FOLLOW_8);
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
    // InternalSTL.g:514:1: rule__Triangle__Group__1__Impl : ( 'facet' ) ;
    public final void rule__Triangle__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:518:1: ( ( 'facet' ) )
            // InternalSTL.g:519:1: ( 'facet' )
            {
            // InternalSTL.g:519:1: ( 'facet' )
            // InternalSTL.g:520:2: 'facet'
            {
             before(grammarAccess.getTriangleAccess().getFacetKeyword_1()); 
            match(input,17,FOLLOW_2); 
             after(grammarAccess.getTriangleAccess().getFacetKeyword_1()); 

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
    // InternalSTL.g:529:1: rule__Triangle__Group__2 : rule__Triangle__Group__2__Impl rule__Triangle__Group__3 ;
    public final void rule__Triangle__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:533:1: ( rule__Triangle__Group__2__Impl rule__Triangle__Group__3 )
            // InternalSTL.g:534:2: rule__Triangle__Group__2__Impl rule__Triangle__Group__3
            {
            pushFollow(FOLLOW_9);
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
    // InternalSTL.g:541:1: rule__Triangle__Group__2__Impl : ( ( rule__Triangle__Group_2__0 ) ) ;
    public final void rule__Triangle__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:545:1: ( ( ( rule__Triangle__Group_2__0 ) ) )
            // InternalSTL.g:546:1: ( ( rule__Triangle__Group_2__0 ) )
            {
            // InternalSTL.g:546:1: ( ( rule__Triangle__Group_2__0 ) )
            // InternalSTL.g:547:2: ( rule__Triangle__Group_2__0 )
            {
             before(grammarAccess.getTriangleAccess().getGroup_2()); 
            // InternalSTL.g:548:2: ( rule__Triangle__Group_2__0 )
            // InternalSTL.g:548:3: rule__Triangle__Group_2__0
            {
            pushFollow(FOLLOW_2);
            rule__Triangle__Group_2__0();

            state._fsp--;


            }

             after(grammarAccess.getTriangleAccess().getGroup_2()); 

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
    // InternalSTL.g:556:1: rule__Triangle__Group__3 : rule__Triangle__Group__3__Impl rule__Triangle__Group__4 ;
    public final void rule__Triangle__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:560:1: ( rule__Triangle__Group__3__Impl rule__Triangle__Group__4 )
            // InternalSTL.g:561:2: rule__Triangle__Group__3__Impl rule__Triangle__Group__4
            {
            pushFollow(FOLLOW_10);
            rule__Triangle__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Triangle__Group__4();

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
    // InternalSTL.g:568:1: rule__Triangle__Group__3__Impl : ( 'outer' ) ;
    public final void rule__Triangle__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:572:1: ( ( 'outer' ) )
            // InternalSTL.g:573:1: ( 'outer' )
            {
            // InternalSTL.g:573:1: ( 'outer' )
            // InternalSTL.g:574:2: 'outer'
            {
             before(grammarAccess.getTriangleAccess().getOuterKeyword_3()); 
            match(input,18,FOLLOW_2); 
             after(grammarAccess.getTriangleAccess().getOuterKeyword_3()); 

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


    // $ANTLR start "rule__Triangle__Group__4"
    // InternalSTL.g:583:1: rule__Triangle__Group__4 : rule__Triangle__Group__4__Impl rule__Triangle__Group__5 ;
    public final void rule__Triangle__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:587:1: ( rule__Triangle__Group__4__Impl rule__Triangle__Group__5 )
            // InternalSTL.g:588:2: rule__Triangle__Group__4__Impl rule__Triangle__Group__5
            {
            pushFollow(FOLLOW_11);
            rule__Triangle__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Triangle__Group__5();

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
    // $ANTLR end "rule__Triangle__Group__4"


    // $ANTLR start "rule__Triangle__Group__4__Impl"
    // InternalSTL.g:595:1: rule__Triangle__Group__4__Impl : ( 'loop' ) ;
    public final void rule__Triangle__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:599:1: ( ( 'loop' ) )
            // InternalSTL.g:600:1: ( 'loop' )
            {
            // InternalSTL.g:600:1: ( 'loop' )
            // InternalSTL.g:601:2: 'loop'
            {
             before(grammarAccess.getTriangleAccess().getLoopKeyword_4()); 
            match(input,19,FOLLOW_2); 
             after(grammarAccess.getTriangleAccess().getLoopKeyword_4()); 

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
    // $ANTLR end "rule__Triangle__Group__4__Impl"


    // $ANTLR start "rule__Triangle__Group__5"
    // InternalSTL.g:610:1: rule__Triangle__Group__5 : rule__Triangle__Group__5__Impl rule__Triangle__Group__6 ;
    public final void rule__Triangle__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:614:1: ( rule__Triangle__Group__5__Impl rule__Triangle__Group__6 )
            // InternalSTL.g:615:2: rule__Triangle__Group__5__Impl rule__Triangle__Group__6
            {
            pushFollow(FOLLOW_11);
            rule__Triangle__Group__5__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Triangle__Group__6();

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
    // $ANTLR end "rule__Triangle__Group__5"


    // $ANTLR start "rule__Triangle__Group__5__Impl"
    // InternalSTL.g:622:1: rule__Triangle__Group__5__Impl : ( ( rule__Triangle__VerticesAssignment_5 )* ) ;
    public final void rule__Triangle__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:626:1: ( ( ( rule__Triangle__VerticesAssignment_5 )* ) )
            // InternalSTL.g:627:1: ( ( rule__Triangle__VerticesAssignment_5 )* )
            {
            // InternalSTL.g:627:1: ( ( rule__Triangle__VerticesAssignment_5 )* )
            // InternalSTL.g:628:2: ( rule__Triangle__VerticesAssignment_5 )*
            {
             before(grammarAccess.getTriangleAccess().getVerticesAssignment_5()); 
            // InternalSTL.g:629:2: ( rule__Triangle__VerticesAssignment_5 )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0==23) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // InternalSTL.g:629:3: rule__Triangle__VerticesAssignment_5
            	    {
            	    pushFollow(FOLLOW_12);
            	    rule__Triangle__VerticesAssignment_5();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop7;
                }
            } while (true);

             after(grammarAccess.getTriangleAccess().getVerticesAssignment_5()); 

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
    // $ANTLR end "rule__Triangle__Group__5__Impl"


    // $ANTLR start "rule__Triangle__Group__6"
    // InternalSTL.g:637:1: rule__Triangle__Group__6 : rule__Triangle__Group__6__Impl rule__Triangle__Group__7 ;
    public final void rule__Triangle__Group__6() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:641:1: ( rule__Triangle__Group__6__Impl rule__Triangle__Group__7 )
            // InternalSTL.g:642:2: rule__Triangle__Group__6__Impl rule__Triangle__Group__7
            {
            pushFollow(FOLLOW_13);
            rule__Triangle__Group__6__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Triangle__Group__7();

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
    // $ANTLR end "rule__Triangle__Group__6"


    // $ANTLR start "rule__Triangle__Group__6__Impl"
    // InternalSTL.g:649:1: rule__Triangle__Group__6__Impl : ( 'endloop' ) ;
    public final void rule__Triangle__Group__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:653:1: ( ( 'endloop' ) )
            // InternalSTL.g:654:1: ( 'endloop' )
            {
            // InternalSTL.g:654:1: ( 'endloop' )
            // InternalSTL.g:655:2: 'endloop'
            {
             before(grammarAccess.getTriangleAccess().getEndloopKeyword_6()); 
            match(input,20,FOLLOW_2); 
             after(grammarAccess.getTriangleAccess().getEndloopKeyword_6()); 

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
    // $ANTLR end "rule__Triangle__Group__6__Impl"


    // $ANTLR start "rule__Triangle__Group__7"
    // InternalSTL.g:664:1: rule__Triangle__Group__7 : rule__Triangle__Group__7__Impl ;
    public final void rule__Triangle__Group__7() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:668:1: ( rule__Triangle__Group__7__Impl )
            // InternalSTL.g:669:2: rule__Triangle__Group__7__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Triangle__Group__7__Impl();

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
    // $ANTLR end "rule__Triangle__Group__7"


    // $ANTLR start "rule__Triangle__Group__7__Impl"
    // InternalSTL.g:675:1: rule__Triangle__Group__7__Impl : ( 'endfacet' ) ;
    public final void rule__Triangle__Group__7__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:679:1: ( ( 'endfacet' ) )
            // InternalSTL.g:680:1: ( 'endfacet' )
            {
            // InternalSTL.g:680:1: ( 'endfacet' )
            // InternalSTL.g:681:2: 'endfacet'
            {
             before(grammarAccess.getTriangleAccess().getEndfacetKeyword_7()); 
            match(input,21,FOLLOW_2); 
             after(grammarAccess.getTriangleAccess().getEndfacetKeyword_7()); 

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
    // $ANTLR end "rule__Triangle__Group__7__Impl"


    // $ANTLR start "rule__Triangle__Group_2__0"
    // InternalSTL.g:691:1: rule__Triangle__Group_2__0 : rule__Triangle__Group_2__0__Impl rule__Triangle__Group_2__1 ;
    public final void rule__Triangle__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:695:1: ( rule__Triangle__Group_2__0__Impl rule__Triangle__Group_2__1 )
            // InternalSTL.g:696:2: rule__Triangle__Group_2__0__Impl rule__Triangle__Group_2__1
            {
            pushFollow(FOLLOW_14);
            rule__Triangle__Group_2__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Triangle__Group_2__1();

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
    // $ANTLR end "rule__Triangle__Group_2__0"


    // $ANTLR start "rule__Triangle__Group_2__0__Impl"
    // InternalSTL.g:703:1: rule__Triangle__Group_2__0__Impl : ( 'normal' ) ;
    public final void rule__Triangle__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:707:1: ( ( 'normal' ) )
            // InternalSTL.g:708:1: ( 'normal' )
            {
            // InternalSTL.g:708:1: ( 'normal' )
            // InternalSTL.g:709:2: 'normal'
            {
             before(grammarAccess.getTriangleAccess().getNormalKeyword_2_0()); 
            match(input,22,FOLLOW_2); 
             after(grammarAccess.getTriangleAccess().getNormalKeyword_2_0()); 

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
    // $ANTLR end "rule__Triangle__Group_2__0__Impl"


    // $ANTLR start "rule__Triangle__Group_2__1"
    // InternalSTL.g:718:1: rule__Triangle__Group_2__1 : rule__Triangle__Group_2__1__Impl ;
    public final void rule__Triangle__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:722:1: ( rule__Triangle__Group_2__1__Impl )
            // InternalSTL.g:723:2: rule__Triangle__Group_2__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Triangle__Group_2__1__Impl();

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
    // $ANTLR end "rule__Triangle__Group_2__1"


    // $ANTLR start "rule__Triangle__Group_2__1__Impl"
    // InternalSTL.g:729:1: rule__Triangle__Group_2__1__Impl : ( ( rule__Triangle__NormalAssignment_2_1 ) ) ;
    public final void rule__Triangle__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:733:1: ( ( ( rule__Triangle__NormalAssignment_2_1 ) ) )
            // InternalSTL.g:734:1: ( ( rule__Triangle__NormalAssignment_2_1 ) )
            {
            // InternalSTL.g:734:1: ( ( rule__Triangle__NormalAssignment_2_1 ) )
            // InternalSTL.g:735:2: ( rule__Triangle__NormalAssignment_2_1 )
            {
             before(grammarAccess.getTriangleAccess().getNormalAssignment_2_1()); 
            // InternalSTL.g:736:2: ( rule__Triangle__NormalAssignment_2_1 )
            // InternalSTL.g:736:3: rule__Triangle__NormalAssignment_2_1
            {
            pushFollow(FOLLOW_2);
            rule__Triangle__NormalAssignment_2_1();

            state._fsp--;


            }

             after(grammarAccess.getTriangleAccess().getNormalAssignment_2_1()); 

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
    // $ANTLR end "rule__Triangle__Group_2__1__Impl"


    // $ANTLR start "rule__Vertex__Group__0"
    // InternalSTL.g:745:1: rule__Vertex__Group__0 : rule__Vertex__Group__0__Impl rule__Vertex__Group__1 ;
    public final void rule__Vertex__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:749:1: ( rule__Vertex__Group__0__Impl rule__Vertex__Group__1 )
            // InternalSTL.g:750:2: rule__Vertex__Group__0__Impl rule__Vertex__Group__1
            {
            pushFollow(FOLLOW_14);
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
    // InternalSTL.g:757:1: rule__Vertex__Group__0__Impl : ( () ) ;
    public final void rule__Vertex__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:761:1: ( ( () ) )
            // InternalSTL.g:762:1: ( () )
            {
            // InternalSTL.g:762:1: ( () )
            // InternalSTL.g:763:2: ()
            {
             before(grammarAccess.getVertexAccess().getVertexAction_0()); 
            // InternalSTL.g:764:2: ()
            // InternalSTL.g:764:3: 
            {
            }

             after(grammarAccess.getVertexAccess().getVertexAction_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Vertex__Group__0__Impl"


    // $ANTLR start "rule__Vertex__Group__1"
    // InternalSTL.g:772:1: rule__Vertex__Group__1 : rule__Vertex__Group__1__Impl rule__Vertex__Group__2 ;
    public final void rule__Vertex__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:776:1: ( rule__Vertex__Group__1__Impl rule__Vertex__Group__2 )
            // InternalSTL.g:777:2: rule__Vertex__Group__1__Impl rule__Vertex__Group__2
            {
            pushFollow(FOLLOW_15);
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
    // InternalSTL.g:784:1: rule__Vertex__Group__1__Impl : ( 'vertex' ) ;
    public final void rule__Vertex__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:788:1: ( ( 'vertex' ) )
            // InternalSTL.g:789:1: ( 'vertex' )
            {
            // InternalSTL.g:789:1: ( 'vertex' )
            // InternalSTL.g:790:2: 'vertex'
            {
             before(grammarAccess.getVertexAccess().getVertexKeyword_1()); 
            match(input,23,FOLLOW_2); 
             after(grammarAccess.getVertexAccess().getVertexKeyword_1()); 

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
    // InternalSTL.g:799:1: rule__Vertex__Group__2 : rule__Vertex__Group__2__Impl rule__Vertex__Group__3 ;
    public final void rule__Vertex__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:803:1: ( rule__Vertex__Group__2__Impl rule__Vertex__Group__3 )
            // InternalSTL.g:804:2: rule__Vertex__Group__2__Impl rule__Vertex__Group__3
            {
            pushFollow(FOLLOW_15);
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
    // InternalSTL.g:811:1: rule__Vertex__Group__2__Impl : ( ( rule__Vertex__XAssignment_2 ) ) ;
    public final void rule__Vertex__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:815:1: ( ( ( rule__Vertex__XAssignment_2 ) ) )
            // InternalSTL.g:816:1: ( ( rule__Vertex__XAssignment_2 ) )
            {
            // InternalSTL.g:816:1: ( ( rule__Vertex__XAssignment_2 ) )
            // InternalSTL.g:817:2: ( rule__Vertex__XAssignment_2 )
            {
             before(grammarAccess.getVertexAccess().getXAssignment_2()); 
            // InternalSTL.g:818:2: ( rule__Vertex__XAssignment_2 )
            // InternalSTL.g:818:3: rule__Vertex__XAssignment_2
            {
            pushFollow(FOLLOW_2);
            rule__Vertex__XAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getVertexAccess().getXAssignment_2()); 

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
    // InternalSTL.g:826:1: rule__Vertex__Group__3 : rule__Vertex__Group__3__Impl rule__Vertex__Group__4 ;
    public final void rule__Vertex__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:830:1: ( rule__Vertex__Group__3__Impl rule__Vertex__Group__4 )
            // InternalSTL.g:831:2: rule__Vertex__Group__3__Impl rule__Vertex__Group__4
            {
            pushFollow(FOLLOW_15);
            rule__Vertex__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Vertex__Group__4();

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
    // InternalSTL.g:838:1: rule__Vertex__Group__3__Impl : ( ( rule__Vertex__YAssignment_3 ) ) ;
    public final void rule__Vertex__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:842:1: ( ( ( rule__Vertex__YAssignment_3 ) ) )
            // InternalSTL.g:843:1: ( ( rule__Vertex__YAssignment_3 ) )
            {
            // InternalSTL.g:843:1: ( ( rule__Vertex__YAssignment_3 ) )
            // InternalSTL.g:844:2: ( rule__Vertex__YAssignment_3 )
            {
             before(grammarAccess.getVertexAccess().getYAssignment_3()); 
            // InternalSTL.g:845:2: ( rule__Vertex__YAssignment_3 )
            // InternalSTL.g:845:3: rule__Vertex__YAssignment_3
            {
            pushFollow(FOLLOW_2);
            rule__Vertex__YAssignment_3();

            state._fsp--;


            }

             after(grammarAccess.getVertexAccess().getYAssignment_3()); 

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


    // $ANTLR start "rule__Vertex__Group__4"
    // InternalSTL.g:853:1: rule__Vertex__Group__4 : rule__Vertex__Group__4__Impl ;
    public final void rule__Vertex__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:857:1: ( rule__Vertex__Group__4__Impl )
            // InternalSTL.g:858:2: rule__Vertex__Group__4__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Vertex__Group__4__Impl();

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
    // $ANTLR end "rule__Vertex__Group__4"


    // $ANTLR start "rule__Vertex__Group__4__Impl"
    // InternalSTL.g:864:1: rule__Vertex__Group__4__Impl : ( ( rule__Vertex__ZAssignment_4 ) ) ;
    public final void rule__Vertex__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:868:1: ( ( ( rule__Vertex__ZAssignment_4 ) ) )
            // InternalSTL.g:869:1: ( ( rule__Vertex__ZAssignment_4 ) )
            {
            // InternalSTL.g:869:1: ( ( rule__Vertex__ZAssignment_4 ) )
            // InternalSTL.g:870:2: ( rule__Vertex__ZAssignment_4 )
            {
             before(grammarAccess.getVertexAccess().getZAssignment_4()); 
            // InternalSTL.g:871:2: ( rule__Vertex__ZAssignment_4 )
            // InternalSTL.g:871:3: rule__Vertex__ZAssignment_4
            {
            pushFollow(FOLLOW_2);
            rule__Vertex__ZAssignment_4();

            state._fsp--;


            }

             after(grammarAccess.getVertexAccess().getZAssignment_4()); 

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
    // $ANTLR end "rule__Vertex__Group__4__Impl"


    // $ANTLR start "rule__EDouble__Group__0"
    // InternalSTL.g:880:1: rule__EDouble__Group__0 : rule__EDouble__Group__0__Impl rule__EDouble__Group__1 ;
    public final void rule__EDouble__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:884:1: ( rule__EDouble__Group__0__Impl rule__EDouble__Group__1 )
            // InternalSTL.g:885:2: rule__EDouble__Group__0__Impl rule__EDouble__Group__1
            {
            pushFollow(FOLLOW_15);
            rule__EDouble__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__EDouble__Group__1();

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
    // $ANTLR end "rule__EDouble__Group__0"


    // $ANTLR start "rule__EDouble__Group__0__Impl"
    // InternalSTL.g:892:1: rule__EDouble__Group__0__Impl : ( ( rule__EDouble__Alternatives_0 )? ) ;
    public final void rule__EDouble__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:896:1: ( ( ( rule__EDouble__Alternatives_0 )? ) )
            // InternalSTL.g:897:1: ( ( rule__EDouble__Alternatives_0 )? )
            {
            // InternalSTL.g:897:1: ( ( rule__EDouble__Alternatives_0 )? )
            // InternalSTL.g:898:2: ( rule__EDouble__Alternatives_0 )?
            {
             before(grammarAccess.getEDoubleAccess().getAlternatives_0()); 
            // InternalSTL.g:899:2: ( rule__EDouble__Alternatives_0 )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( ((LA8_0>=11 && LA8_0<=12)) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // InternalSTL.g:899:3: rule__EDouble__Alternatives_0
                    {
                    pushFollow(FOLLOW_2);
                    rule__EDouble__Alternatives_0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getEDoubleAccess().getAlternatives_0()); 

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
    // $ANTLR end "rule__EDouble__Group__0__Impl"


    // $ANTLR start "rule__EDouble__Group__1"
    // InternalSTL.g:907:1: rule__EDouble__Group__1 : rule__EDouble__Group__1__Impl rule__EDouble__Group__2 ;
    public final void rule__EDouble__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:911:1: ( rule__EDouble__Group__1__Impl rule__EDouble__Group__2 )
            // InternalSTL.g:912:2: rule__EDouble__Group__1__Impl rule__EDouble__Group__2
            {
            pushFollow(FOLLOW_15);
            rule__EDouble__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__EDouble__Group__2();

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
    // $ANTLR end "rule__EDouble__Group__1"


    // $ANTLR start "rule__EDouble__Group__1__Impl"
    // InternalSTL.g:919:1: rule__EDouble__Group__1__Impl : ( ( RULE_INT )? ) ;
    public final void rule__EDouble__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:923:1: ( ( ( RULE_INT )? ) )
            // InternalSTL.g:924:1: ( ( RULE_INT )? )
            {
            // InternalSTL.g:924:1: ( ( RULE_INT )? )
            // InternalSTL.g:925:2: ( RULE_INT )?
            {
             before(grammarAccess.getEDoubleAccess().getINTTerminalRuleCall_1()); 
            // InternalSTL.g:926:2: ( RULE_INT )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==RULE_INT) ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // InternalSTL.g:926:3: RULE_INT
                    {
                    match(input,RULE_INT,FOLLOW_2); 

                    }
                    break;

            }

             after(grammarAccess.getEDoubleAccess().getINTTerminalRuleCall_1()); 

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
    // $ANTLR end "rule__EDouble__Group__1__Impl"


    // $ANTLR start "rule__EDouble__Group__2"
    // InternalSTL.g:934:1: rule__EDouble__Group__2 : rule__EDouble__Group__2__Impl rule__EDouble__Group__3 ;
    public final void rule__EDouble__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:938:1: ( rule__EDouble__Group__2__Impl rule__EDouble__Group__3 )
            // InternalSTL.g:939:2: rule__EDouble__Group__2__Impl rule__EDouble__Group__3
            {
            pushFollow(FOLLOW_16);
            rule__EDouble__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__EDouble__Group__3();

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
    // $ANTLR end "rule__EDouble__Group__2"


    // $ANTLR start "rule__EDouble__Group__2__Impl"
    // InternalSTL.g:946:1: rule__EDouble__Group__2__Impl : ( '.' ) ;
    public final void rule__EDouble__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:950:1: ( ( '.' ) )
            // InternalSTL.g:951:1: ( '.' )
            {
            // InternalSTL.g:951:1: ( '.' )
            // InternalSTL.g:952:2: '.'
            {
             before(grammarAccess.getEDoubleAccess().getFullStopKeyword_2()); 
            match(input,24,FOLLOW_2); 
             after(grammarAccess.getEDoubleAccess().getFullStopKeyword_2()); 

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
    // $ANTLR end "rule__EDouble__Group__2__Impl"


    // $ANTLR start "rule__EDouble__Group__3"
    // InternalSTL.g:961:1: rule__EDouble__Group__3 : rule__EDouble__Group__3__Impl rule__EDouble__Group__4 ;
    public final void rule__EDouble__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:965:1: ( rule__EDouble__Group__3__Impl rule__EDouble__Group__4 )
            // InternalSTL.g:966:2: rule__EDouble__Group__3__Impl rule__EDouble__Group__4
            {
            pushFollow(FOLLOW_17);
            rule__EDouble__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__EDouble__Group__4();

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
    // $ANTLR end "rule__EDouble__Group__3"


    // $ANTLR start "rule__EDouble__Group__3__Impl"
    // InternalSTL.g:973:1: rule__EDouble__Group__3__Impl : ( RULE_INT ) ;
    public final void rule__EDouble__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:977:1: ( ( RULE_INT ) )
            // InternalSTL.g:978:1: ( RULE_INT )
            {
            // InternalSTL.g:978:1: ( RULE_INT )
            // InternalSTL.g:979:2: RULE_INT
            {
             before(grammarAccess.getEDoubleAccess().getINTTerminalRuleCall_3()); 
            match(input,RULE_INT,FOLLOW_2); 
             after(grammarAccess.getEDoubleAccess().getINTTerminalRuleCall_3()); 

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
    // $ANTLR end "rule__EDouble__Group__3__Impl"


    // $ANTLR start "rule__EDouble__Group__4"
    // InternalSTL.g:988:1: rule__EDouble__Group__4 : rule__EDouble__Group__4__Impl ;
    public final void rule__EDouble__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:992:1: ( rule__EDouble__Group__4__Impl )
            // InternalSTL.g:993:2: rule__EDouble__Group__4__Impl
            {
            pushFollow(FOLLOW_2);
            rule__EDouble__Group__4__Impl();

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
    // $ANTLR end "rule__EDouble__Group__4"


    // $ANTLR start "rule__EDouble__Group__4__Impl"
    // InternalSTL.g:999:1: rule__EDouble__Group__4__Impl : ( ( rule__EDouble__Group_4__0 )? ) ;
    public final void rule__EDouble__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:1003:1: ( ( ( rule__EDouble__Group_4__0 )? ) )
            // InternalSTL.g:1004:1: ( ( rule__EDouble__Group_4__0 )? )
            {
            // InternalSTL.g:1004:1: ( ( rule__EDouble__Group_4__0 )? )
            // InternalSTL.g:1005:2: ( rule__EDouble__Group_4__0 )?
            {
             before(grammarAccess.getEDoubleAccess().getGroup_4()); 
            // InternalSTL.g:1006:2: ( rule__EDouble__Group_4__0 )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( ((LA10_0>=13 && LA10_0<=14)) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // InternalSTL.g:1006:3: rule__EDouble__Group_4__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__EDouble__Group_4__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getEDoubleAccess().getGroup_4()); 

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
    // $ANTLR end "rule__EDouble__Group__4__Impl"


    // $ANTLR start "rule__EDouble__Group_4__0"
    // InternalSTL.g:1015:1: rule__EDouble__Group_4__0 : rule__EDouble__Group_4__0__Impl rule__EDouble__Group_4__1 ;
    public final void rule__EDouble__Group_4__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:1019:1: ( rule__EDouble__Group_4__0__Impl rule__EDouble__Group_4__1 )
            // InternalSTL.g:1020:2: rule__EDouble__Group_4__0__Impl rule__EDouble__Group_4__1
            {
            pushFollow(FOLLOW_18);
            rule__EDouble__Group_4__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__EDouble__Group_4__1();

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
    // $ANTLR end "rule__EDouble__Group_4__0"


    // $ANTLR start "rule__EDouble__Group_4__0__Impl"
    // InternalSTL.g:1027:1: rule__EDouble__Group_4__0__Impl : ( ( rule__EDouble__Alternatives_4_0 ) ) ;
    public final void rule__EDouble__Group_4__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:1031:1: ( ( ( rule__EDouble__Alternatives_4_0 ) ) )
            // InternalSTL.g:1032:1: ( ( rule__EDouble__Alternatives_4_0 ) )
            {
            // InternalSTL.g:1032:1: ( ( rule__EDouble__Alternatives_4_0 ) )
            // InternalSTL.g:1033:2: ( rule__EDouble__Alternatives_4_0 )
            {
             before(grammarAccess.getEDoubleAccess().getAlternatives_4_0()); 
            // InternalSTL.g:1034:2: ( rule__EDouble__Alternatives_4_0 )
            // InternalSTL.g:1034:3: rule__EDouble__Alternatives_4_0
            {
            pushFollow(FOLLOW_2);
            rule__EDouble__Alternatives_4_0();

            state._fsp--;


            }

             after(grammarAccess.getEDoubleAccess().getAlternatives_4_0()); 

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
    // $ANTLR end "rule__EDouble__Group_4__0__Impl"


    // $ANTLR start "rule__EDouble__Group_4__1"
    // InternalSTL.g:1042:1: rule__EDouble__Group_4__1 : rule__EDouble__Group_4__1__Impl rule__EDouble__Group_4__2 ;
    public final void rule__EDouble__Group_4__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:1046:1: ( rule__EDouble__Group_4__1__Impl rule__EDouble__Group_4__2 )
            // InternalSTL.g:1047:2: rule__EDouble__Group_4__1__Impl rule__EDouble__Group_4__2
            {
            pushFollow(FOLLOW_18);
            rule__EDouble__Group_4__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__EDouble__Group_4__2();

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
    // $ANTLR end "rule__EDouble__Group_4__1"


    // $ANTLR start "rule__EDouble__Group_4__1__Impl"
    // InternalSTL.g:1054:1: rule__EDouble__Group_4__1__Impl : ( ( rule__EDouble__Alternatives_4_1 )? ) ;
    public final void rule__EDouble__Group_4__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:1058:1: ( ( ( rule__EDouble__Alternatives_4_1 )? ) )
            // InternalSTL.g:1059:1: ( ( rule__EDouble__Alternatives_4_1 )? )
            {
            // InternalSTL.g:1059:1: ( ( rule__EDouble__Alternatives_4_1 )? )
            // InternalSTL.g:1060:2: ( rule__EDouble__Alternatives_4_1 )?
            {
             before(grammarAccess.getEDoubleAccess().getAlternatives_4_1()); 
            // InternalSTL.g:1061:2: ( rule__EDouble__Alternatives_4_1 )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( ((LA11_0>=11 && LA11_0<=12)) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // InternalSTL.g:1061:3: rule__EDouble__Alternatives_4_1
                    {
                    pushFollow(FOLLOW_2);
                    rule__EDouble__Alternatives_4_1();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getEDoubleAccess().getAlternatives_4_1()); 

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
    // $ANTLR end "rule__EDouble__Group_4__1__Impl"


    // $ANTLR start "rule__EDouble__Group_4__2"
    // InternalSTL.g:1069:1: rule__EDouble__Group_4__2 : rule__EDouble__Group_4__2__Impl ;
    public final void rule__EDouble__Group_4__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:1073:1: ( rule__EDouble__Group_4__2__Impl )
            // InternalSTL.g:1074:2: rule__EDouble__Group_4__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__EDouble__Group_4__2__Impl();

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
    // $ANTLR end "rule__EDouble__Group_4__2"


    // $ANTLR start "rule__EDouble__Group_4__2__Impl"
    // InternalSTL.g:1080:1: rule__EDouble__Group_4__2__Impl : ( RULE_INT ) ;
    public final void rule__EDouble__Group_4__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:1084:1: ( ( RULE_INT ) )
            // InternalSTL.g:1085:1: ( RULE_INT )
            {
            // InternalSTL.g:1085:1: ( RULE_INT )
            // InternalSTL.g:1086:2: RULE_INT
            {
             before(grammarAccess.getEDoubleAccess().getINTTerminalRuleCall_4_2()); 
            match(input,RULE_INT,FOLLOW_2); 
             after(grammarAccess.getEDoubleAccess().getINTTerminalRuleCall_4_2()); 

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
    // $ANTLR end "rule__EDouble__Group_4__2__Impl"


    // $ANTLR start "rule__Geometry__NameAssignment_2"
    // InternalSTL.g:1096:1: rule__Geometry__NameAssignment_2 : ( ruleEString ) ;
    public final void rule__Geometry__NameAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:1100:1: ( ( ruleEString ) )
            // InternalSTL.g:1101:2: ( ruleEString )
            {
            // InternalSTL.g:1101:2: ( ruleEString )
            // InternalSTL.g:1102:3: ruleEString
            {
             before(grammarAccess.getGeometryAccess().getNameEStringParserRuleCall_2_0()); 
            pushFollow(FOLLOW_2);
            ruleEString();

            state._fsp--;

             after(grammarAccess.getGeometryAccess().getNameEStringParserRuleCall_2_0()); 

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
    // $ANTLR end "rule__Geometry__NameAssignment_2"


    // $ANTLR start "rule__Geometry__NodesAssignment_3"
    // InternalSTL.g:1111:1: rule__Geometry__NodesAssignment_3 : ( ruleShape_Impl ) ;
    public final void rule__Geometry__NodesAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:1115:1: ( ( ruleShape_Impl ) )
            // InternalSTL.g:1116:2: ( ruleShape_Impl )
            {
            // InternalSTL.g:1116:2: ( ruleShape_Impl )
            // InternalSTL.g:1117:3: ruleShape_Impl
            {
             before(grammarAccess.getGeometryAccess().getNodesShape_ImplParserRuleCall_3_0()); 
            pushFollow(FOLLOW_2);
            ruleShape_Impl();

            state._fsp--;

             after(grammarAccess.getGeometryAccess().getNodesShape_ImplParserRuleCall_3_0()); 

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
    // $ANTLR end "rule__Geometry__NodesAssignment_3"


    // $ANTLR start "rule__Shape_Impl__TrianglesAssignment_1"
    // InternalSTL.g:1126:1: rule__Shape_Impl__TrianglesAssignment_1 : ( ruleTriangle ) ;
    public final void rule__Shape_Impl__TrianglesAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:1130:1: ( ( ruleTriangle ) )
            // InternalSTL.g:1131:2: ( ruleTriangle )
            {
            // InternalSTL.g:1131:2: ( ruleTriangle )
            // InternalSTL.g:1132:3: ruleTriangle
            {
             before(grammarAccess.getShape_ImplAccess().getTrianglesTriangleParserRuleCall_1_0()); 
            pushFollow(FOLLOW_2);
            ruleTriangle();

            state._fsp--;

             after(grammarAccess.getShape_ImplAccess().getTrianglesTriangleParserRuleCall_1_0()); 

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
    // $ANTLR end "rule__Shape_Impl__TrianglesAssignment_1"


    // $ANTLR start "rule__Triangle__NormalAssignment_2_1"
    // InternalSTL.g:1141:1: rule__Triangle__NormalAssignment_2_1 : ( ruleVertex ) ;
    public final void rule__Triangle__NormalAssignment_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:1145:1: ( ( ruleVertex ) )
            // InternalSTL.g:1146:2: ( ruleVertex )
            {
            // InternalSTL.g:1146:2: ( ruleVertex )
            // InternalSTL.g:1147:3: ruleVertex
            {
             before(grammarAccess.getTriangleAccess().getNormalVertexParserRuleCall_2_1_0()); 
            pushFollow(FOLLOW_2);
            ruleVertex();

            state._fsp--;

             after(grammarAccess.getTriangleAccess().getNormalVertexParserRuleCall_2_1_0()); 

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
    // $ANTLR end "rule__Triangle__NormalAssignment_2_1"


    // $ANTLR start "rule__Triangle__VerticesAssignment_5"
    // InternalSTL.g:1156:1: rule__Triangle__VerticesAssignment_5 : ( ruleVertex ) ;
    public final void rule__Triangle__VerticesAssignment_5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:1160:1: ( ( ruleVertex ) )
            // InternalSTL.g:1161:2: ( ruleVertex )
            {
            // InternalSTL.g:1161:2: ( ruleVertex )
            // InternalSTL.g:1162:3: ruleVertex
            {
             before(grammarAccess.getTriangleAccess().getVerticesVertexParserRuleCall_5_0()); 
            pushFollow(FOLLOW_2);
            ruleVertex();

            state._fsp--;

             after(grammarAccess.getTriangleAccess().getVerticesVertexParserRuleCall_5_0()); 

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
    // $ANTLR end "rule__Triangle__VerticesAssignment_5"


    // $ANTLR start "rule__Vertex__XAssignment_2"
    // InternalSTL.g:1171:1: rule__Vertex__XAssignment_2 : ( ruleEDouble ) ;
    public final void rule__Vertex__XAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:1175:1: ( ( ruleEDouble ) )
            // InternalSTL.g:1176:2: ( ruleEDouble )
            {
            // InternalSTL.g:1176:2: ( ruleEDouble )
            // InternalSTL.g:1177:3: ruleEDouble
            {
             before(grammarAccess.getVertexAccess().getXEDoubleParserRuleCall_2_0()); 
            pushFollow(FOLLOW_2);
            ruleEDouble();

            state._fsp--;

             after(grammarAccess.getVertexAccess().getXEDoubleParserRuleCall_2_0()); 

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
    // $ANTLR end "rule__Vertex__XAssignment_2"


    // $ANTLR start "rule__Vertex__YAssignment_3"
    // InternalSTL.g:1186:1: rule__Vertex__YAssignment_3 : ( ruleEDouble ) ;
    public final void rule__Vertex__YAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:1190:1: ( ( ruleEDouble ) )
            // InternalSTL.g:1191:2: ( ruleEDouble )
            {
            // InternalSTL.g:1191:2: ( ruleEDouble )
            // InternalSTL.g:1192:3: ruleEDouble
            {
             before(grammarAccess.getVertexAccess().getYEDoubleParserRuleCall_3_0()); 
            pushFollow(FOLLOW_2);
            ruleEDouble();

            state._fsp--;

             after(grammarAccess.getVertexAccess().getYEDoubleParserRuleCall_3_0()); 

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
    // $ANTLR end "rule__Vertex__YAssignment_3"


    // $ANTLR start "rule__Vertex__ZAssignment_4"
    // InternalSTL.g:1201:1: rule__Vertex__ZAssignment_4 : ( ruleEDouble ) ;
    public final void rule__Vertex__ZAssignment_4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSTL.g:1205:1: ( ( ruleEDouble ) )
            // InternalSTL.g:1206:2: ( ruleEDouble )
            {
            // InternalSTL.g:1206:2: ( ruleEDouble )
            // InternalSTL.g:1207:3: ruleEDouble
            {
             before(grammarAccess.getVertexAccess().getZEDoubleParserRuleCall_4_0()); 
            pushFollow(FOLLOW_2);
            ruleEDouble();

            state._fsp--;

             after(grammarAccess.getVertexAccess().getZEDoubleParserRuleCall_4_0()); 

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
    // $ANTLR end "rule__Vertex__ZAssignment_4"

    // Delegated rules


 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000000000020030L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000000000020002L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0000000000900000L});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0000000000800002L});
    public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_15 = new BitSet(new long[]{0x0000000001001840L});
    public static final BitSet FOLLOW_16 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_17 = new BitSet(new long[]{0x0000000000006000L});
    public static final BitSet FOLLOW_18 = new BitSet(new long[]{0x0000000000001840L});

}