

import java.util.Hashtable;
import java.util.Vector;
import Jakarta.util.FixDosOutputStream;
import java.io.*;

// generated by GenCT

public class UmodSmDecl {
    public void reduce2java( AstProperties props ) {
        String        stmp;
        SmExtendsClause tmp;
        AST_Class result;
        AST_QualifiedName sdname;
        AST_FieldDecl   curr_state;
        int i;

        // Step 0: we can't reduce a state machine declaration unless
		  //         it has a Delivery clause.  Check for this before
		  //         we go on.
		  //         arg[3] -- SmClassBody
 		  //         arg[0].arg[0] -- RootClause

		 if (arg[3].arg[0].arg[0] == null) 
		    AstNode.fatalError( "Refinement of State Machine missing "
			    + "Delivery_parameters declaration" ); 

        // Step 1: basic initialization: remember output directory name,
        //         create an Sm object, and assign it a name

        kernelConstants.globals().sm4vars.ser_directory = ( String ) props.getProperty( "outputDirectory" );
        kernelConstants.globals().sm4vars.Sm = new  sdInfo();
        kernelConstants.globals().sm4vars.Sm.name = arg[0].tok[0].tokenName();

        // Step 2: get the name of the superSm.  The name is null if 
        //         arg[1].arg[0] is null or if arg[1].arg[0] instanceof 
        //         SmClsExtends

        tmp = ( SmExtendsClause ) arg[1].arg[0];
        if ( tmp == null || tmp instanceof  SmClsExtends )
            kernelConstants.globals().sm4vars.Sm.superSm_name = null;
        else
            kernelConstants.globals().sm4vars.Sm.superSm_name = 
               ( ( AST_QualifiedName ) tmp.arg[0].arg[0] ).GetName();

        // Step 3: harvest values that are available through UmodSmDecl;
        //         other values will be assigned by reducing UmodSmDecl args

        // I know the following assignment is ugly, but this is the simplest way to 
        // generate a correct ExtClause parse tree 

        kernelConstants.globals().sm4vars.Sm.extends_ast = arg[1];
        if ( tmp != null ) {
            AST_QualifiedName qn = ( AST_QualifiedName ) tmp.getQualifiedName().clone();
            qn.Detach();
            kernelConstants.globals().sm4vars.Sm.extends_ast = new  AstOptNode().setParms( new  ExtClause().setParms( new  AstToken().setParms( " ","extends", 0 ),  qn ) );
        }
        kernelConstants.globals().sm4vars.Sm.impls_ast   = arg[2];
        kernelConstants.globals().sm4vars.Sm.body_ast    = null;
        kernelConstants.globals().sm4vars.Sm.pardecl_ast = null;
        kernelConstants.globals().sm4vars.Sm.pardecl     = "";
        kernelConstants.globals().sm4vars.Sm.argdecl_ast = null;
        kernelConstants.globals().sm4vars.Sm.argdecl     = "";
        kernelConstants.globals().sm4vars.Sm.otherwise_default_ast = ( kernelConstants.globals().sm4vars.Sm.superSm_name == null )?
            kernelConstants.globals().sm4vars.ToStmt( ";" ) : null;

        // Step 4: inherit properties (if any)

        if ( kernelConstants.globals().sm4vars.Sm.superSm_name != null )
            inheritProperties();

        // reduce only the SmClassBody

        arg[3].reduce2java( props );

        sdname =  AST_QualifiedName.Make( kernelConstants.globals().sm4vars.Sm.name );

        // curr_state and disp are null (represent no code) if the
        // current state machine has a superSm.  If not, then we generate
        // the code to declare the current state and dispatch variables.

        if ( kernelConstants.globals().sm4vars.Sm.superSm_name == null ) {
            AST_ParList pl  = ( AST_ParList )  kernelConstants.globals().sm4vars.Sm.pardecl_ast.clone();
            AST_ParList pl2 = ( AST_ParList ) pl.clone();
            AST_ArgList aa  = ( AST_ArgList )  kernelConstants.globals().sm4vars.Sm.argdecl_ast.clone();
            pl.Detach();
            pl2.Detach();
            aa.Detach();

            curr_state =(AST_FieldDecl) AstNode.markStack(AstNode.aliasStack.size(),  (AST_FieldDecl) new AST_FieldDecl()

.add( (AST_FieldDeclElem) new AST_FieldDeclElem().setParms( (FldVarDec) new FldVarDec().setParms( 
 new AstOptNode(
).setParms( (AST_Modifiers) new AST_Modifiers()

.add( (AST_ModifiersElem) new AST_ModifiersElem().setParms( (ModProtected) new ModProtected().setParms( 
new AstToken().setParms("\r\n            ","protected", 0)) /* ModProtected */
))/* AST_ModifiersElem + add */
) /* AstOptNode */
,  (PrimType) new PrimType().setParms( 
 (IntTyp) new IntTyp().setParms( 
new AstToken().setParms(" ","int", 0)) /* IntTyp */
,  new AstOptNode(
) /* AstOptNode */
) /* PrimType */
,  (AST_VarDecl) new AST_VarDecl()

.add( (AST_VarDeclElem) new AST_VarDeclElem().setParms( (VarDecl) new VarDecl().setParms( 
 (DecNameDim) new DecNameDim().setParms( 
 (NameId) new NameId().setParms(new AstToken().setParms(" ","current_state", 0)) /* NameId */
,  new AstOptNode(
) /* AstOptNode */
) /* DecNameDim */
,  new AstOptNode(
).setParms( (VarAssignC) new VarAssignC().setParms( 
new AstToken().setParms(" ","=", 0),  (VarInitExpr) new VarInitExpr().setParms( 
 (PPQualName) new PPQualName().setParms( 
 (AST_QualifiedName) new AST_QualifiedName()

.add( (AST_QualifiedNameElem) new AST_QualifiedNameElem().setParms( (NameId) new NameId().setParms(new AstToken().setParms(" ","start", 0)) /* NameId */
))/* AST_QualifiedNameElem + add */
) /* PPQualName */
) /* VarInitExpr */
) /* VarAssignC */
) /* AstOptNode */
) /* VarDecl */
))/* AST_VarDeclElem + add */
, new AstToken().setParms("",";", 0)) /* FldVarDec */
))/* AST_FieldDeclElem + add */

.add( (AST_FieldDeclElem) new AST_FieldDeclElem().setParms( (MethodDcl) new MethodDcl().setParms( 
 new AstOptNode(
).setParms( (AST_Modifiers) new AST_Modifiers()

.add( (AST_ModifiersElem) new AST_ModifiersElem().setParms( (ModFinal) new ModFinal().setParms( 
new AstToken().setParms("\r\n            ","final", 0)) /* ModFinal */
))/* AST_ModifiersElem + add */

.add( (AST_ModifiersElem) new AST_ModifiersElem().setParms( (ModPublic) new ModPublic().setParms( 
new AstToken().setParms(" ","public", 0)) /* ModPublic */
))/* AST_ModifiersElem + add */
) /* AstOptNode */
,  (PrimType) new PrimType().setParms( 
 (BoolTyp) new BoolTyp().setParms( 
new AstToken().setParms(" ","boolean", 0)) /* BoolTyp */
,  new AstOptNode(
) /* AstOptNode */
) /* PrimType */
,  (MthDector) new MthDector().setParms( 
 (NameId) new NameId().setParms(new AstToken().setParms(" ","atStop", 0)) /* NameId */
, new AstToken().setParms("","(", 0),  new AstOptNode(
) /* AstOptNode */
, new AstToken().setParms("",")", 0),  new AstOptNode(
) /* AstOptNode */
) /* MthDector */
,  new AstOptNode(
) /* AstOptNode */
,  (MDSBlock) new MDSBlock().setParms( 
 (BlockC) new BlockC().setParms( 
new AstToken().setParms(" ","{", 0),  new AstOptNode(
).setParms( (AST_Stmt) new AST_Stmt()

.add( (AST_StmtElem) new AST_StmtElem().setParms( (ReturnStm) new ReturnStm().setParms( 
new AstToken().setParms("\r\n                ","return", 0),  new AstOptNode(
).setParms( (EqExpr) new EqExpr().setParms( 
 (PPQualName) new PPQualName().setParms( 
 (AST_QualifiedName) new AST_QualifiedName()

.add( (AST_QualifiedNameElem) new AST_QualifiedNameElem().setParms( (NameId) new NameId().setParms(new AstToken().setParms(" ","current_state", 0)) /* NameId */
))/* AST_QualifiedNameElem + add */
) /* PPQualName */
,  (MoreEqExpr) new MoreEqExpr()

.add( (MoreEqExprElem) new MoreEqExprElem().setParms( (EEBodyC) new EEBodyC().setParms( 
 (Eq) new Eq().setParms( 
new AstToken().setParms(" ","==", 0)) /* Eq */
,  (PPQualName) new PPQualName().setParms( 
 (AST_QualifiedName) new AST_QualifiedName()

.add( (AST_QualifiedNameElem) new AST_QualifiedNameElem().setParms( (NameId) new NameId().setParms(new AstToken().setParms(" ","stop", 0)) /* NameId */
))/* AST_QualifiedNameElem + add */
) /* PPQualName */
) /* EEBodyC */
))/* MoreEqExprElem + add */
) /* EqExpr */
) /* AstOptNode */
, new AstToken().setParms("",";", 0)) /* ReturnStm */
))/* AST_StmtElem + add */
) /* AstOptNode */
, new AstToken().setParms("\r\n            ","}", 0)) /* BlockC */
) /* MDSBlock */
) /* MethodDcl */
))/* AST_FieldDeclElem + add */

.add( (AST_FieldDeclElem) new AST_FieldDeclElem().setParms( (NInterDecl) new NInterDecl().setParms( 
 new AstOptNode(
).setParms( (AST_Modifiers) new AST_Modifiers()

.add( (AST_ModifiersElem) new AST_ModifiersElem().setParms( (ModProtected) new ModProtected().setParms( 
new AstToken().setParms("\r\n            ","protected", 0)) /* ModProtected */
))/* AST_ModifiersElem + add */
) /* AstOptNode */
,  (UmInterDecl) new UmInterDecl().setParms( 
new AstToken().setParms(" ","interface", 0),  (NameId) new NameId().setParms(new AstToken().setParms(" ","disp", 0)) /* NameId */
,  new AstOptNode(
) /* AstOptNode */
, new AstToken().setParms(" ","{", 0),  new AstOptNode(
).setParms( (InterfaceMemberDeclarations) new InterfaceMemberDeclarations()

.add( (InterfaceMemberDeclarationsElem) new InterfaceMemberDeclarationsElem().setParms( (MDecl) new MDecl().setParms( 
 (MethodDcl) new MethodDcl().setParms( 
 new AstOptNode(
) /* AstOptNode */
,  (PrimType) new PrimType().setParms( 
 (VoidTyp) new VoidTyp().setParms( 
new AstToken().setParms("\r\n                ","void", 0)) /* VoidTyp */
,  new AstOptNode(
) /* AstOptNode */
) /* PrimType */
,  (MthDector) new MthDector().setParms( 
 (NameId) new NameId().setParms(new AstToken().setParms(" ","exit", 0)) /* NameId */
, new AstToken().setParms("","(", 0),  new AstOptNode(
).setParms( (AST_ParList) new AST_ParList()

.add( (AST_ParList) AstNode.addComment( AstNode.safeCopy( pl)," ")) 
) /* AstOptNode */
, new AstToken().setParms(" ",")", 0),  new AstOptNode(
) /* AstOptNode */
) /* MthDector */
,  new AstOptNode(
) /* AstOptNode */
,  (MDSEmpty) new MDSEmpty().setParms( 
new AstToken().setParms("",";", 0)) /* MDSEmpty */
) /* MethodDcl */
) /* MDecl */
))/* InterfaceMemberDeclarationsElem + add */
) /* AstOptNode */
, new AstToken().setParms("\r\n            ","}", 0)) /* UmInterDecl */
) /* NInterDecl */
))/* AST_FieldDeclElem + add */

.add( (AST_FieldDeclElem) new AST_FieldDeclElem().setParms( (FldVarDec) new FldVarDec().setParms( 
 new AstOptNode(
).setParms( (AST_Modifiers) new AST_Modifiers()

.add( (AST_ModifiersElem) new AST_ModifiersElem().setParms( (ModProtected) new ModProtected().setParms( 
new AstToken().setParms("\r\n            ","protected", 0)) /* ModProtected */
))/* AST_ModifiersElem + add */
) /* AstOptNode */
,  (QNameType) new QNameType().setParms( 
 (AST_QualifiedName) new AST_QualifiedName()

.add( (AST_QualifiedNameElem) new AST_QualifiedNameElem().setParms( (NameId) new NameId().setParms(new AstToken().setParms(" ","disp", 0)) /* NameId */
))/* AST_QualifiedNameElem + add */
,  new AstOptNode(
) /* AstOptNode */
) /* QNameType */
,  (AST_VarDecl) new AST_VarDecl()

.add( (AST_VarDeclElem) new AST_VarDeclElem().setParms( (VarDecl) new VarDecl().setParms( 
 (DecNameDim) new DecNameDim().setParms( 
 (NameId) new NameId().setParms(new AstToken().setParms(" ","dispatch", 0)) /* NameId */
,  new AstOptNode(
) /* AstOptNode */
) /* DecNameDim */
,  new AstOptNode(
).setParms( (VarAssignC) new VarAssignC().setParms( 
new AstToken().setParms(" ","=", 0),  (VarInitExpr) new VarInitExpr().setParms( 
 (ObjAllocExpr) new ObjAllocExpr().setParms( 
new AstToken().setParms(" ","new", 0),  (AST_QualifiedName) new AST_QualifiedName()

.add( (AST_QualifiedNameElem) new AST_QualifiedNameElem().setParms( (NameId) new NameId().setParms(new AstToken().setParms(" ","disp", 0)) /* NameId */
))/* AST_QualifiedNameElem + add */
,  (AnonClass) new AnonClass().setParms( 
 (Args) new Args().setParms( 
new AstToken().setParms("","(", 0),  new AstOptNode(
) /* AstOptNode */
, new AstToken().setParms("",")", 0)) /* Args */
,  new AstOptNode(
).setParms( (ClsBody) new ClsBody().setParms( 
new AstToken().setParms(" ","{", 0),  new AstOptNode(
).setParms( (AST_FieldDecl) new AST_FieldDecl()

.add( (AST_FieldDeclElem) new AST_FieldDeclElem().setParms( (MethodDcl) new MethodDcl().setParms( 
 new AstOptNode(
).setParms( (AST_Modifiers) new AST_Modifiers()

.add( (AST_ModifiersElem) new AST_ModifiersElem().setParms( (ModPublic) new ModPublic().setParms( 
new AstToken().setParms("\r\n                ","public", 0)) /* ModPublic */
))/* AST_ModifiersElem + add */
) /* AstOptNode */
,  (PrimType) new PrimType().setParms( 
 (VoidTyp) new VoidTyp().setParms( 
new AstToken().setParms(" ","void", 0)) /* VoidTyp */
,  new AstOptNode(
) /* AstOptNode */
) /* PrimType */
,  (MthDector) new MthDector().setParms( 
 (NameId) new NameId().setParms(new AstToken().setParms(" ","exit", 0)) /* NameId */
, new AstToken().setParms("","(", 0),  new AstOptNode(
).setParms( (AST_ParList) new AST_ParList()

.add( (AST_ParList) AstNode.addComment( AstNode.safeCopy( pl2)," ")) 
) /* AstOptNode */
, new AstToken().setParms(" ",")", 0),  new AstOptNode(
) /* AstOptNode */
) /* MthDector */
,  new AstOptNode(
) /* AstOptNode */
,  (MDSBlock) new MDSBlock().setParms( 
 (BlockC) new BlockC().setParms( 
new AstToken().setParms(" ","{", 0),  new AstOptNode(
).setParms( (AST_Stmt) new AST_Stmt()

.add( (AST_StmtElem) new AST_StmtElem().setParms( (ExprStmt) new ExprStmt().setParms( 
 (PrimExpr) new PrimExpr().setParms( 
 (PPQualName) new PPQualName().setParms( 
 (AST_QualifiedName) new AST_QualifiedName()

.add( (AST_QualifiedNameElem) new AST_QualifiedNameElem().setParms( (NameId) new NameId().setParms(new AstToken().setParms("\r\n                    ","start_exit", 0)) /* NameId */
))/* AST_QualifiedNameElem + add */
) /* PPQualName */
,  (Suffixes) new Suffixes()

.add( (SuffixesElem) new SuffixesElem().setParms( (MthCall) new MthCall().setParms( 
 (Args) new Args().setParms( 
new AstToken().setParms("","(", 0),  new AstOptNode(
).setParms( (AST_ArgList) new AST_ArgList()

.add( (AST_ArgList) AstNode.addComment( AstNode.safeCopy( aa)," ")) 
) /* AstOptNode */
, new AstToken().setParms(" ",")", 0)) /* Args */
) /* MthCall */
))/* SuffixesElem + add */
) /* PrimExpr */
, new AstToken().setParms("",";", 0)) /* ExprStmt */
))/* AST_StmtElem + add */
) /* AstOptNode */
, new AstToken().setParms("\r\n                ","}", 0)) /* BlockC */
) /* MDSBlock */
) /* MethodDcl */
))/* AST_FieldDeclElem + add */
) /* AstOptNode */
, new AstToken().setParms("\r\n            ","}", 0)) /* ClsBody */
) /* AstOptNode */
) /* AnonClass */
) /* ObjAllocExpr */
) /* VarInitExpr */
) /* VarAssignC */
) /* AstOptNode */
) /* VarDecl */
))/* AST_VarDeclElem + add */
, new AstToken().setParms("",";", 0)) /* FldVarDec */
))/* AST_FieldDeclElem + add */
).patch()
;
        }
        else
            curr_state = null;

        AST_FieldDecl cbody = new  AST_FieldDecl();
        cbody.add( curr_state );
        cbody.add( state_constants() );
        cbody.add( nested_state_vars() );
        cbody.add( test_methods() );
        cbody.add( action_methods() );
        cbody.add( branch_methods() );
		  cbody.add( starBranch_method() );
        cbody.add( otherwise_methods() );
        cbody.add( exit_methods() );
        cbody.add( enter_methods() );
        cbody.add( prepare_methods() );
        cbody.add( delivery_method() );
        cbody.add( getState_method() );
        cbody.add( extra_methods() );
        cbody = cbody.BodySort();
        cbody.add( kernelConstants.globals().sm4vars.Sm.body_ast );

        result =(AST_Class) AstNode.markStack(AstNode.aliasStack.size(),  (AST_Class) new AST_Class()

.add( (AST_ClassElem) new AST_ClassElem().setParms( (ModTypeDecl) new ModTypeDecl().setParms( 
 new AstOptNode(
) /* AstOptNode */
,  (UmodClassDecl) new UmodClassDecl().setParms( 
new AstToken().setParms("\r\n\r\n        ","class", 0), ( (AST_QualifiedName) AstNode.addComment( AstNode.safeCopy( sdname)," ") ).makeQName(),  new AstOptNode(
) /* AstOptNode */
,  new AstOptNode(
) /* AstOptNode */
,  (ClsBody) new ClsBody().setParms( 
new AstToken().setParms(" ","{", 0),  new AstOptNode(
).setParms( (AST_FieldDecl) new AST_FieldDecl()

.add( (AST_FieldDecl) AstNode.addComment( AstNode.safeCopy( cbody),"\r\n            ")) 
) /* AstOptNode */
, new AstToken().setParms("\r\n        ","}", 0)) /* ClsBody */
) /* UmodClassDecl */
) /* ModTypeDecl */
))/* AST_ClassElem + add */
).patch()
;

        // don't forget to remember the white space in front of
        // the state machine declaration

        result.setComment( getComment() );

        // finally, tack on extends and impls declarations.
        // we can't use cls{ class $id(...) extends $tlst(...) implements
        // $tlst(...) {...} }cls because if either $tlst argument is
        // empty, then we produce an incorrect parse tree... sigh...

        AstCursor k = new  AstCursor();
        for ( k.First( result ); k.More(); k.PlusPlus() ) {
            if ( k.node instanceof  UmodClassDecl ) {
                k.node.arg[1] =  kernelConstants.globals().sm4vars.Sm.extends_ast;
                k.node.arg[2] =  kernelConstants.globals().sm4vars.Sm.impls_ast;
                break;
            }
        }

        // output reduction *before* serialization

        result.normalize();
        result.reduce2java( props );

        kernelConstants.globals().sm4vars.Sm.truncate(); // remove unnecessary ASTs
        Utility.writeObjectToFile( kernelConstants.globals().sm4vars.Sm, Utility.SerFileName( kernelConstants.globals().sm4vars.Sm.name ) );
    }

    // generator of extra_methods

    AST_FieldDecl extra_methods() {
        return kernelConstants.globals().sm4vars.ToFieldDecl( kernelConstants.globals().sm4vars.Sm.extra_methods );
    }
        
    // generator of otherwise_methods

    AST_FieldDecl otherwise_methods() {
        stateInfo s;
        String result = "";

        // add default_otherwise method if we're not refining or 
        // a generic otherwise clause was defined

        if ( kernelConstants.globals().sm4vars.Sm.superSm_name == null || 
             kernelConstants.globals().sm4vars.Sm.otherwise_default_ast != null )
            result = "\n   // otherwise_Default method\n" +
                    "   void otherwise_Default(" +  kernelConstants.globals().sm4vars.Sm.pardecl + ") { " +
                     kernelConstants.globals().sm4vars.ToString( kernelConstants.globals().sm4vars.Sm.otherwise_default_ast ) + " } ";

        SmIterator i =  kernelConstants.globals().sm4vars.Sm.StateCont.iterator();
        for ( s = ( stateInfo ) i.firstObj(); 
              s != null; 
              s = ( stateInfo ) i.nextObj() ) {
       
            // generate an otherwise method if the state is new
            // or an otherwise statement was encountered. 

            if ( !s.inherited || s.otherwise_action_ast != null )
                result = result + "\n" +
                        "   void " + s.name + "_otherwise(" +  kernelConstants.globals().sm4vars.Sm.pardecl + 
                        ") { " +  kernelConstants.globals().sm4vars.ToString( s.otherwise_action_ast ) + "}";
        }
        return kernelConstants.globals().sm4vars.ToFieldDecl( result );
    }

    // generator of state_constants

    AST_FieldDecl state_constants() {
	     String header;

		  // if there is no superSM, then generate a static constant
		  if (kernelConstants.globals().sm4vars.Sm.superSm_name == null)
		     header = "   static int stateNumGenerator = 0;\n";
		  else
		     header = "\n";

        return kernelConstants.globals().sm4vars.ToFieldDecl( 
		     header +  kernelConstants.globals().sm4vars.Sm.state_constants );
    }

    // generator of test methods

    AST_FieldDecl test_methods() {
        String result = "\n";
        transInfo e;

        SmIterator i =  kernelConstants.globals().sm4vars.Sm.TransCont.iterator();
        for ( e = ( transInfo ) i.firstObj(); 
              e != null; 
              e = ( transInfo ) i.nextObj() ) {

            // generate test method if (a) transition defined in file
            // or (b) test method refinement defined in file

            if ( ! ( e.defined_in_file || e.condition_ast != null ) )
                continue; // generate nothing

            result = result +
               "   boolean " + e.name + 
                   "_test( " +  kernelConstants.globals().sm4vars.Sm.pardecl + " ) { return " + 
                   e.condition_ast + "; }\n";
        }
        return kernelConstants.globals().sm4vars.ToFieldDecl( result );
    }

    // generator of action methods

    AST_FieldDecl action_methods() {
        transInfo e;
        String result = "";

        SmIterator i =  kernelConstants.globals().sm4vars.Sm.TransCont.iterator();
        for ( e = ( transInfo ) i.firstObj(); 
              e != null; 
              e = ( transInfo ) i.nextObj() ) {

            // generate action method if (a) transition defined in file
            // or (b) action method refinement defined in file

            if ( ! ( e.defined_in_file || e.action_ast != null ) )
                continue; // generate nothing

            String mth = "\n\n   // methods for transition " + e.name + "\n\n";
            mth = mth + "   void " + e.name + 
                  "_action(" +  kernelConstants.globals().sm4vars.Sm.pardecl + ") { " + 
                   kernelConstants.globals().sm4vars.ToString( e.action_ast ) + " }";
            result = result + mth;
        }
        return kernelConstants.globals().sm4vars.ToFieldDecl( result );
    }

    // generator of branch methods

    AST_FieldDecl branch_methods() {
        String result = "";
        stateInfo s;

        SmIterator i =  kernelConstants.globals().sm4vars.Sm.StateCont.iterator();
        for ( s = ( stateInfo ) i.firstObj(); 
              s != null; 
              s = ( stateInfo ) i.nextObj() ) {

            result = result + s.branches_method();
        }
        return kernelConstants.globals().sm4vars.ToFieldDecl( result );
    }

	 // generator of starBranch method

	 AST_FieldDecl starBranch_method() {
        String result = "";
        transInfo t;

        SmIterator i =  kernelConstants.globals().sm4vars.Sm.TransCont.iterator();
        for ( t = ( transInfo ) i.firstObj(); 
              t != null; 
              t = ( transInfo ) i.nextObj() ) {

            // for each *-transition defined in the current machine
				// generate a *-branch

            if ( t.start.equals( "*" ) && !t.inherited )
            result = result + t.branches;
        }

        // now define the starBranches method.  We don't have
		  // to generate anything if we are inheriting from another
		  // state machine AND we have defined no * edges.  Otherwise
		  // we have to generate a starBranches method.

        boolean haveStars = !result.equals("");
		  boolean root = kernelConstants.globals().sm4vars.Sm.superSm_name == null;
		  if ( haveStars || root ) {
		     String pars = kernelConstants.globals().sm4vars.Sm.pardecl;
			  String args = kernelConstants.globals().sm4vars.Sm.argdecl;

		     result = "\n   boolean starBranches( " + pars + " ) { \n      " 
			           + result;
			  if ( !root )
			     result = result + " return super.starBranches( " + args + " ); };";
			  else
      	     result = result + " return true; };";
		  }
        return kernelConstants.globals().sm4vars.ToFieldDecl( result );
    }


    // generator of exit methods

    AST_FieldDecl exit_methods() {
        stateInfo s;
        String result = "";

        SmIterator i =  kernelConstants.globals().sm4vars.Sm.StateCont.iterator();
        for ( s = ( stateInfo ) i.firstObj(); 
              s != null; 
              s = ( stateInfo ) i.nextObj() ) {

            // always define an exit_user method if (a) the state is not inherited
            // or (b) an exit_action was specified

            if ( !s.inherited || s.exit_action_ast != null ) {
                result = result + "\n" +
              "   void " + s.name + "_exit( " +  kernelConstants.globals().sm4vars.Sm.pardecl + 
              " ) { " +  kernelConstants.globals().sm4vars.ToString( s.exit_action_ast ) + " }";
            }
        }
        return kernelConstants.globals().sm4vars.ToFieldDecl( result );
    }

    // generator of enter methods

    AST_FieldDecl enter_methods() {
        stateInfo s;
        String result = "";
        String nested_state_stuff = "";

        SmIterator i =  kernelConstants.globals().sm4vars.Sm.StateCont.iterator();
        for ( s = ( stateInfo ) i.firstObj(); 
              s != null; 
              s = ( stateInfo ) i.nextObj() ) {

            if ( s.nested_state && !s.inherited ) {
                nested_state_stuff = "\n" +
"      " + s.nested_var + " = " + s.alloc_expr_ast.toString() + ";\n";
            }

            // define an enter method if the state is not inherited.
            // this method contains generated code followed by a call to _enter_user
            // a method that contains user-specified code

            if ( !s.inherited ) {
                result = result + "\n" +
                 "   void " + s.name + "_enter( " +  kernelConstants.globals().sm4vars.Sm.pardecl + " ) {\n" + 
                 "      current_state = " + s.name + ";\n" +
                 "      dispatch = new disp() { public void exit( " + 
                  kernelConstants.globals().sm4vars.Sm.pardecl + 
                 " ) { " + s.name + "_exit( " +  kernelConstants.globals().sm4vars.Sm.argdecl + " ); } };\n" +
                 nested_state_stuff + "\n" +
                 "      " + s.name + "_enter_user(" +  kernelConstants.globals().sm4vars.Sm.argdecl + ");\n" +
                 "    }";
            }

            // always define an enter_user method if (a) the state is not inherited
            // or (b) an enter_action was specified

            if ( !s.inherited || s.enter_action_ast != null ) {
                result = result + "\n" +
              "   void " + s.name + "_enter_user( " +  kernelConstants.globals().sm4vars.Sm.pardecl + 
              " ) { " +  kernelConstants.globals().sm4vars.ToString( s.enter_action_ast ) +
              "\n   }";
            }
        }
        return kernelConstants.globals().sm4vars.ToFieldDecl( result );
    }

    // generator of prepare methods

    AST_FieldDecl prepare_methods() {
        stateInfo s;
        String result = "";
        String nested_state_stuff = "";

        SmIterator i =  kernelConstants.globals().sm4vars.Sm.StateCont.iterator();
        for ( s = ( stateInfo ) i.firstObj(); 
              s != null; 
              s = ( stateInfo ) i.nextObj() ) {

            // always define a prepare method if (a) the state is not inherited
            // or (b) an prepare_action was specified 

            if ( !s.inherited || s.prepare_action_ast != null ) {
                result = result + "\n" +
              "   void " + s.name + "_prepare( " +  kernelConstants.globals().sm4vars.Sm.pardecl + 
              " ) { " +  kernelConstants.globals().sm4vars.ToString( s.prepare_action_ast ) + " }";
            }
        }
        return kernelConstants.globals().sm4vars.ToFieldDecl( result );
    }

    // generator of the delivery method

    AST_FieldDecl delivery_method() {
        String result;
        String st        = "";
        String terminate = "";
        stateInfo     s;

        SmIterator i =  kernelConstants.globals().sm4vars.Sm.StateCont.iterator();
        for ( s = ( stateInfo ) i.firstObj(); 
              s != null; 
              s = ( stateInfo ) i.nextObj() ) {

            if ( !s.inherited )
                st = st + s.delivery_action;
        }

        // when no new states are added, don't generate a delivery method

        if ( kernelConstants.globals().sm4vars.Sm.superSm_name != null && st.equals( "" ) )
            return null;

        if ( kernelConstants.globals().sm4vars.Sm.superSm_name == null )
            terminate =  kernelConstants.globals().sm4vars.ToString( kernelConstants.globals().sm4vars.Sm.no_trans_ast );
        else
            terminate =  "super.delivery( " +  kernelConstants.globals().sm4vars.Sm.argdecl + " );";

        result = "\n      // delivery method\n\n" +
"   public void delivery( " +  kernelConstants.globals().sm4vars.Sm.pardecl + " ) {" +
"      " + st + "\n" +
"      " + terminate + "\n" +
"   }";
        return kernelConstants.globals().sm4vars.ToFieldDecl( result );
    }

    // generator of nested state variables

    AST_FieldDecl nested_state_vars() {
        stateInfo s;
        String          result = "";

        SmIterator i =  kernelConstants.globals().sm4vars.Sm.StateCont.iterator();
        for ( s = ( stateInfo ) i.firstObj(); 
              s != null; 
              s = ( stateInfo ) i.nextObj() ) {

            if ( !s.inherited && s.nested_state ) {
                String typeName =  
               ( ( AST_QualifiedName ) s.alloc_expr_ast.arg[0] ).GetName();
                result = result + "\n" + "   protected " + typeName + 
                       " " + s.nested_var + " = null;";
            }
        }

        return kernelConstants.globals().sm4vars.ToFieldDecl( result );
    }

    // generator of the getState method

    AST_FieldDecl getState_method() {
        stateInfo s;
        String          st = "";
        String          terminate;
        String          result;

        SmIterator i =  kernelConstants.globals().sm4vars.Sm.StateCont.iterator();
        for ( s = ( stateInfo ) i.firstObj(); 
              s != null; 
              s = ( stateInfo ) i.nextObj() ) {

            if ( !s.inherited ) {
                st = st + 
"      if (current_state == " + s.name + ") return \"" + s.name + "\";\n";
            }
        }

        // when no new states are added, don't generate a delivery method

        if ( kernelConstants.globals().sm4vars.Sm.superSm_name != null && st.equals( "" ) )
            return null;

        if ( kernelConstants.globals().sm4vars.Sm.superSm_name == null )
            terminate =
"      return \"Unrecognizable_State\";\n";
        else
            terminate = 
"      return super.getState();\n";

        result =  "\n\n" +
"   // getState method\n\n" +
"   public String getState() {\n" + st + terminate +
"   }\n";

        return kernelConstants.globals().sm4vars.ToFieldDecl( result );
    }

    // properties of parent (w.r.t. inheritance hierarchy) state machines
    // are inherited by this method

    // This method now does nothing -- nothing is inherited
    public void inheritProperties() { }
}