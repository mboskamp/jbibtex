/* BibTeXParser.java */
/* Generated By:JavaCC: Do not edit this line. BibTeXParser.java */
package org.jbibtex;

import java.io.FileReader;
import java.io.Reader;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BibTeXParser implements BibTeXParserConstants {

        private KeyMap<BibTeXString> macros = new KeyMap<BibTeXString>();

        private BibTeXDatabase database = null;

        private List<Exception> exceptions = new ArrayList<Exception>();

        private long id = 1L;


        public BibTeXParser() throws ParseException, TokenMgrException {
                this("");

                // Default macros
                addMacro("jan", "January");
                addMacro("feb", "February");
                addMacro("mar", "March");
                addMacro("apr", "April");
                addMacro("may", "May");
                addMacro("jun", "June");
                addMacro("jul", "July");
                addMacro("aug", "August");
                addMacro("sep", "September");
                addMacro("oct", "October");
                addMacro("nov", "November");
                addMacro("dec", "December");
        }

        /**
	 * Parses the BibTeX document in "normal" mode.
	 * The parser throws an exception is an error condition is detected.
	 *
	 * @throws ObjectResolutionException If the database is inconsistent.
	 *
	 * @throws TokenMgrException If a token cannot be produced.
	 * @throws ParseException If the produced sequence of tokens cannot be consumed.
	 *
	 * @see CharacterFilterReader
	 */
        public BibTeXDatabase parse(Reader reader) throws ObjectResolutionException, ParseException, TokenMgrException {
                ReInit(new StreamProvider(reader));

                token_source.SwitchTo(IN_HEADER);

                return Database(false);
        }

        /**
	 * Parses the BibTeX document in "error recovery" mode.
	 * The parser does its best to recover from typical error conditions by skipping the problematic object definition.
	 * The parser maintains a {@link #getExceptions() list of exceptions}. This list is cleared when a parse method is invoked again.
	 *
	 * @throws ObjectResolutionException If the database is inconsistent.
	 *
	 * @see CharacterFilterReader
	 */
        public BibTeXDatabase parseFully(Reader reader) throws ObjectResolutionException {
                ReInit(new StreamProvider(reader));

                token_source.SwitchTo(IN_HEADER);

                try {
                        return Database(true);
                } catch(TokenMgrException tme){
                        throw new RuntimeException(tme);
                } catch(ParseException pe){
                        throw new RuntimeException(pe);
                }
        }

        /**
	 * Checks the <code>string</code> argument before constructing a new {@link ReferenceValue} instance.
	 * The default behaviour is to prohibit unresolved references.
	 *
	 * @throws ObjectResolutionException If the <code>string</code> is <code>null</code>.
	 *
	 * @see BibTeXDatabase#resolveString(Key)
	 * @see BibTeXParser#getMacros()
	 */
        public void checkStringResolution(Key key, BibTeXString string){

                if(string == null){
                        throw new ObjectResolutionException(key);
                }
        }

        private void resolveCrossReferences(){
                Map<Key, BibTeXEntry> entries = getDatabase().getEntries();

                for(BibTeXEntry entry : entries.values()){
                        Map<Key, Value> fields = entry.getFields();

                        Value value = fields.get(BibTeXEntry.KEY_CROSSREF);
                        if((value == null) || (value instanceof CrossReferenceValue)){
                                continue;
                        }

                        Key key = new Key(value.toUserString());

                        BibTeXEntry object = getDatabase().resolveEntry(key);
                        checkCrossReferenceResolution(key, object);

                        entry.addField(BibTeXEntry.KEY_CROSSREF, new CrossReferenceValue(value, object));
                }
        }

        /**
	 * Checks the <code>entry</code> argument before constructing a new {@link CrossReferenceValue} instance.
	 * The default behaviour is to prohibit unresolved references.
	 *
	 * @throws ObjectResolutionException If the <code>entry</code> is <code>null</code>.
	 *
	 * @see BibTeXDatabase#resolveEntry(Key)
	 */
        public void checkCrossReferenceResolution(Key key, BibTeXEntry entry){

                if(entry == null){
                        throw new ObjectResolutionException(key);
                }
        }

        /**
	 * Generates a key value for an anonymous BibTeX bibliography database entry.
	 *
	 * @see BibTeXEntry#getKey
	 * @see BibTeXDatabase#resolveEntry(Key)
	 */
        public String nextKey(){
                return String.valueOf("jbibtex-" + this.id++);
        }

        public BibTeXDatabase getDatabase(){
                return this.database;
        }

        private void setDatabase(BibTeXDatabase database){
                this.database = database;
        }

        public List<Exception> getExceptions(){
                return Collections.unmodifiableList(this.exceptions);
        }

        public KeyMap<BibTeXString> getMacros(){
                return this.macros;
        }

        public void addMacro(String key, String value){
                addMacro(new BibTeXString(new Key(key), new StringValue(value, StringValue.Style.BRACED)));
        }

        public void addMacro(BibTeXString macro){
                this.macros.put(macro.getKey(), macro);
        }

        public void removeMacro(String key){
                this.macros.remove(new Key(key));
        }

        public void removeMacro(BibTeXString macro){
                this.macros.remove(macro.getKey());
        }

        static
        private boolean isDigitString(String string){

                for(int i = 0; i < string.length(); i++){
                        char c = string.charAt(i);

                        // Only ASCII digits from '0' to '9'
                        if(c < '0' || c > '9'){
                                return false;
                        }
                }

                return (string.length() > 0);
        }

        static
        private class Field {

                private Key key = null;

                private Value value = null;


                private Field(Key key, Value value){
                        setKey(key);
                        setValue(value);
                }

                public Key getKey(){
                        return this.key;
                }

                private void setKey(Key key){
                        this.key = key;
                }

                public Value getValue(){
                        return this.value;
                }

                private void setValue(Value value){
                        this.value = value;
                }
        }

  final private BibTeXDatabase Database(boolean performErrorRecovery) throws ParseException {BibTeXDatabase database = new BibTeXDatabase();

        BibTeXObject object = null;
setDatabase(database);

                this.exceptions.clear();
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case AT:
      case COMMA:{
        ;
        break;
        }
      default:
        jj_la1[0] = jj_gen;
        break label_1;
      }
      try {
        switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
        case COMMA:{
          jj_consume_token(COMMA);
          break;
          }
        case AT:{
          object = Object();
database.addObject(object);
          break;
          }
        default:
          jj_la1[1] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
      } catch (TokenMgrException tme) {
if(!performErrorRecovery){
                                {if (true) throw tme;}
                        }

                        this.exceptions.add(tme);

                        SkipTo("@");
      } catch (ParseException pe) {
if(!performErrorRecovery){
                                {if (true) throw pe;}
                        }

                        this.exceptions.add(pe);

                        SkipTo("@");
      }
    }
    jj_consume_token(0);
resolveCrossReferences();

                setDatabase(null);

                return database;
  }

  final private BibTeXObject Object() throws ParseException {BibTeXObject object;
    if (jj_2_1(2147483647)) {
      object = Comment();
    } else if (jj_2_2(2147483647)) {
      object = Include();
    } else if (jj_2_3(2147483647)) {
      object = Preamble();
    } else if (jj_2_4(2147483647)) {
      object = String();
    } else {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case AT:{
        object = Entry();
        break;
        }
      default:
        jj_la1[2] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
return object;
  }

  final private BibTeXComment Comment() throws ParseException {String string;
    jj_consume_token(AT);
    jj_consume_token(COMMENT);
    jj_consume_token(LBRACE);
    string = Literal(1, "}");
return new BibTeXComment(new StringValue(string, StringValue.Style.BRACED));
  }

  final private BibTeXInclude Include() throws ParseException {String string;
    jj_consume_token(AT);
    jj_consume_token(INCLUDE);
    jj_consume_token(LBRACE);
    string = Literal(1, "}");
BibTeXDatabase database;

                try {
                        Reader reader = new FileReader(string);

                        try {
                                BibTeXParser parser = new BibTeXParser();

                                database = parser.parse(reader);
                        } finally {
                                reader.close();
                        }
                } catch(Exception e){
                        {if (true) throw new ParseException(e.getMessage());}
                }

                return new BibTeXInclude(new StringValue(string, StringValue.Style.BRACED), database);
  }

  final private BibTeXPreamble Preamble() throws ParseException {Value value;
    jj_consume_token(AT);
    jj_consume_token(PREAMBLE);
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case LPAREN:{
      jj_consume_token(LPAREN);
      value = Value();
      jj_consume_token(RPAREN);
      break;
      }
    case LBRACE:{
      jj_consume_token(LBRACE);
      value = Value();
      jj_consume_token(RBRACE);
      break;
      }
    default:
      jj_la1[3] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
return new BibTeXPreamble(value);
  }

  final private BibTeXString String() throws ParseException {Field field;
    jj_consume_token(AT);
    jj_consume_token(STRING);
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case LPAREN:{
      jj_consume_token(LPAREN);
      field = Assignment();
      jj_consume_token(RPAREN);
      break;
      }
    case LBRACE:{
      jj_consume_token(LBRACE);
      field = Assignment();
      jj_consume_token(RBRACE);
      break;
      }
    default:
      jj_la1[4] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
return new BibTeXString(field.getKey(), field.getValue());
  }

  final private BibTeXEntry Entry() throws ParseException {Token type;

        Token key = null;

        List<Field> fields;
    jj_consume_token(AT);
    type = jj_consume_token(NAME);
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case LPAREN:{
      jj_consume_token(LPAREN);
      if (jj_2_5(2147483647)) {
        key = Identifier();
        jj_consume_token(COMMA);
        fields = AssignmentList();
      } else {
        switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
        case COMMA:
        case COMMENT:
        case INCLUDE:
        case PREAMBLE:
        case STRING:
        case NAME:{
          switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
          case COMMA:{
            jj_consume_token(COMMA);
            break;
            }
          default:
            jj_la1[5] = jj_gen;
            ;
          }
          fields = AssignmentList();
          break;
          }
        default:
          jj_la1[6] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
      }
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case COMMA:{
        jj_consume_token(COMMA);
        break;
        }
      default:
        jj_la1[7] = jj_gen;
        ;
      }
      jj_consume_token(RPAREN);
      break;
      }
    case LBRACE:{
      jj_consume_token(LBRACE);
      if (jj_2_6(2147483647)) {
        key = Identifier();
        jj_consume_token(COMMA);
        fields = AssignmentList();
      } else {
        switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
        case COMMA:
        case COMMENT:
        case INCLUDE:
        case PREAMBLE:
        case STRING:
        case NAME:{
          switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
          case COMMA:{
            jj_consume_token(COMMA);
            break;
            }
          default:
            jj_la1[8] = jj_gen;
            ;
          }
          fields = AssignmentList();
          break;
          }
        default:
          jj_la1[9] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
      }
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case COMMA:{
        jj_consume_token(COMMA);
        break;
        }
      default:
        jj_la1[10] = jj_gen;
        ;
      }
      jj_consume_token(RBRACE);
      break;
      }
    default:
      jj_la1[11] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
BibTeXEntry entry = new BibTeXEntry(new Key(type.image), key != null ? new Key(key.image) : new Key(nextKey()));

                for(Field field : fields){
                        entry.addField(field.getKey(), field.getValue());
                }

                return entry;
  }

  final private Field Assignment() throws ParseException {Key key;

        Value value;
    key = Key();
    jj_consume_token(EQUALS);
    value = Value();
return new Field(key, value);
  }

  final private List<Field> AssignmentList() throws ParseException {List<Field> fields = new ArrayList<Field>();

        Field field;
    field = Assignment();
fields.add(field);
    label_2:
    while (true) {
      if (jj_2_7(2147483647)) {
        ;
      } else {
        break label_2;
      }
      jj_consume_token(COMMA);
      field = Assignment();
fields.add(field);
    }
return fields;
  }

  final private Token Identifier() throws ParseException {Token token;
    token = Name();
return token;
  }

  final private Token Name() throws ParseException {Token token;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case COMMENT:{
      token = jj_consume_token(COMMENT);
      break;
      }
    case INCLUDE:{
      token = jj_consume_token(INCLUDE);
      break;
      }
    case PREAMBLE:{
      token = jj_consume_token(PREAMBLE);
      break;
      }
    case STRING:{
      token = jj_consume_token(STRING);
      break;
      }
    case NAME:{
      token = jj_consume_token(NAME);
      break;
      }
    default:
      jj_la1[12] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
return token;
  }

  final private Key Key() throws ParseException {Token token;
    token = Name();
return new Key(token.image);
  }

  final private Value Value() throws ParseException {Value left;

        Value right = null;
    left = SimpleValue();
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case HASH:{
      jj_consume_token(HASH);
      right = Value();
      break;
      }
    default:
      jj_la1[13] = jj_gen;
      ;
    }
if(right != null){
                        return new ConcateValue(left, right);
                }

                return left;
  }

  final private Value SimpleValue() throws ParseException {String string;

        Token token;

        Value result;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case LBRACE:{
      jj_consume_token(LBRACE);
      string = Literal(1, "}");
result = new StringValue(string, StringValue.Style.BRACED);
      break;
      }
    case QUOTE:{
      jj_consume_token(QUOTE);
      string = Literal(0, "\"");
result = new StringValue(string, StringValue.Style.QUOTED);
      break;
      }
    case COMMENT:
    case INCLUDE:
    case PREAMBLE:
    case STRING:
    case NAME:{
      token = Name();
boolean digits = isDigitString(token.image);

                        if(!digits){
                                KeyValue value = new KeyValue(token.image);

                                Key key = value.toKey();

                                BibTeXString object = getDatabase().resolveString(key);
                                if(object == null){
                                        KeyMap<BibTeXString> macros = getMacros();

                                        object = macros.get(key);
                                }

                                checkStringResolution(key, object);

                                result = new ReferenceValue(value, object);
                        } else

                        {
                                result = new DigitStringValue(token.image);
                        }
      break;
      }
    default:
      jj_la1[14] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
return result;
  }

  private void SkipTo(String symbol) throws ParseException, ParseException {if(symbol == null){
                throw new IllegalArgumentException();
        }

        SetState(IN_LITERAL);

        for(Token prevToken = null, token = getNextToken(); true; prevToken = token, token = getNextToken()){

                if(token.kind == BibTeXParserConstants.EOF){
                        throw new ParseException("Reached unexpected EOF token");
                } // End if

                if(symbol.equals(token.image)){
                        break;
                }
        }

        token_source.backup(symbol.length());

        SetState(DEFAULT);
  }

  private String Literal(int braceLevel, String delimiter) throws ParseException, ParseException {StringBuilder sb = new StringBuilder(64);

        if(braceLevel < 0){
                throw new IllegalArgumentException();
        }

        SetState(IN_LITERAL);

        for(Token prevToken = null, token = getNextToken(); true; prevToken = token, token = getNextToken()){

                if(token.kind == BibTeXParserConstants.EOF){
                        throw new ParseException("Reached unexpected EOF token");
                }

                boolean escaped = (prevToken != null && "\\".equals(prevToken.image));

                if(!escaped){

                        if("{".equals(token.image)){
                                braceLevel++;
                        } else

                        if("}".equals(token.image)){
                                braceLevel--;
                        }
                } // End if

                if(delimiter.equals(token.image) && braceLevel == 0){
                        break;
                }

                sb.append(token.image);
        }

        SetState(DEFAULT);

        return StringUtil.removeIndent(sb.toString());
  }

  private void SetState(int state) throws ParseException {if (state != token_source.curLexState) {
                Token root = new Token(), last=root;
                root.next = null;

                // First, we build a list of tokens to push back, in backwards order
                while (token.next != null) {
                        Token t = token;
                        // Find the token whose token.next is the last in the chain
                        while (t.next != null && t.next.next != null)
                                t = t.next;

                        // put it at the end of the new chain
                        last.next = t.next;
                        last = t.next;

                        // If there are special tokens, these go before the regular tokens,
                        // so we want to push them back onto the input stream in the order
                        // we find them along the specialToken chain.
                        if (t.next.specialToken != null) {
                                Token tt=t.next.specialToken;
                                while (tt != null) {
                                        last.next = tt;
                                        last = tt;
                                        tt.next = null;
                                        tt = tt.specialToken;
                                }
                        }
                        t.next = null;
                };

                while (root.next != null) {
                        token_source.backup(root.next.image.length());
                        root.next = root.next.next;
                }
                jj_ntk = -1;
                token_source.SwitchTo(state);
        }
  }

  private boolean jj_2_1(int xla)
 {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_1(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(0, xla); }
  }

  private boolean jj_2_2(int xla)
 {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_2(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(1, xla); }
  }

  private boolean jj_2_3(int xla)
 {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_3(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(2, xla); }
  }

  private boolean jj_2_4(int xla)
 {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_4(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(3, xla); }
  }

  private boolean jj_2_5(int xla)
 {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_5(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(4, xla); }
  }

  private boolean jj_2_6(int xla)
 {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_6(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(5, xla); }
  }

  private boolean jj_2_7(int xla)
 {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_7(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(6, xla); }
  }

  private boolean jj_3R_3()
 {
    if (jj_3R_4()) return true;
    return false;
  }

  private boolean jj_3_7()
 {
    if (jj_scan_token(COMMA)) return true;
    if (jj_3R_4()) return true;
    return false;
  }

  private boolean jj_3_6()
 {
    if (jj_3R_3()) return true;
    if (jj_scan_token(COMMA)) return true;
    return false;
  }

  private boolean jj_3_4()
 {
    if (jj_scan_token(AT)) return true;
    if (jj_scan_token(STRING)) return true;
    return false;
  }

  private boolean jj_3R_4()
 {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_scan_token(20)) {
    jj_scanpos = xsp;
    if (jj_scan_token(21)) {
    jj_scanpos = xsp;
    if (jj_scan_token(22)) {
    jj_scanpos = xsp;
    if (jj_scan_token(23)) {
    jj_scanpos = xsp;
    if (jj_scan_token(24)) return true;
    }
    }
    }
    }
    return false;
  }

  private boolean jj_3_3()
 {
    if (jj_scan_token(AT)) return true;
    if (jj_scan_token(PREAMBLE)) return true;
    return false;
  }

  private boolean jj_3_5()
 {
    if (jj_3R_3()) return true;
    if (jj_scan_token(COMMA)) return true;
    return false;
  }

  private boolean jj_3_2()
 {
    if (jj_scan_token(AT)) return true;
    if (jj_scan_token(INCLUDE)) return true;
    return false;
  }

  private boolean jj_3_1()
 {
    if (jj_scan_token(AT)) return true;
    if (jj_scan_token(COMMENT)) return true;
    return false;
  }

  /** Generated Token Manager. */
  public BibTeXParserTokenManager token_source;
  SimpleCharStream jj_input_stream;
  /** Current token. */
  public Token token;
  /** Next token. */
  public Token jj_nt;
  private int jj_ntk;
  private Token jj_scanpos, jj_lastpos;
  private int jj_la;
  private int jj_gen;
  final private int[] jj_la1 = new int[15];
  static private int[] jj_la1_0;
  static {
      jj_la1_init_0();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x1800,0x1800,0x800,0x18000,0x18000,0x1000,0x1f01000,0x1000,0x1000,0x1f01000,0x1000,0x18000,0x1f00000,0x4000,0x1f28000,};
   }
  final private JJCalls[] jj_2_rtns = new JJCalls[7];
  private boolean jj_rescan = false;
  private int jj_gc = 0;

  /** Constructor. */
  public BibTeXParser(Provider stream) {
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new BibTeXParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 15; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Constructor. */
  public BibTeXParser(String dsl) throws ParseException, TokenMgrException {
      this(new StringProvider(dsl));
  }

  public void ReInit(String s) {
     ReInit(new StringProvider(s));
  }
  /** Reinitialise. */
  public void ReInit(Provider stream) {
	if (jj_input_stream == null) {
      jj_input_stream = new SimpleCharStream(stream, 1, 1);
   } else {
      jj_input_stream.ReInit(stream, 1, 1);
   }
   if (token_source == null) {
      token_source = new BibTeXParserTokenManager(jj_input_stream);
   }

    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 15; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Constructor with generated Token Manager. */
  public BibTeXParser(BibTeXParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 15; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  public void ReInit(BibTeXParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 15; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      if (++jj_gc > 100) {
        jj_gc = 0;
        for (int i = 0; i < jj_2_rtns.length; i++) {
          JJCalls c = jj_2_rtns[i];
          while (c != null) {
            if (c.gen < jj_gen) c.first = null;
            c = c.next;
          }
        }
      }
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }

  @SuppressWarnings("serial")
  static private final class LookaheadSuccess extends java.lang.RuntimeException { }
  final private LookaheadSuccess jj_ls = new LookaheadSuccess();
  private boolean jj_scan_token(int kind) {
    if (jj_scanpos == jj_lastpos) {
      jj_la--;
      if (jj_scanpos.next == null) {
        jj_lastpos = jj_scanpos = jj_scanpos.next = token_source.getNextToken();
      } else {
        jj_lastpos = jj_scanpos = jj_scanpos.next;
      }
    } else {
      jj_scanpos = jj_scanpos.next;
    }
    if (jj_rescan) {
      int i = 0; Token tok = token;
      while (tok != null && tok != jj_scanpos) { i++; tok = tok.next; }
      if (tok != null) jj_add_error_token(kind, i);
    }
    if (jj_scanpos.kind != kind) return true;
    if (jj_la == 0 && jj_scanpos == jj_lastpos) throw jj_ls;
    return false;
  }


/** Get the next Token. */
  final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  private int jj_ntk_f() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  private int[] jj_expentry;
  private int jj_kind = -1;
  private int[] jj_lasttokens = new int[100];
  private int jj_endpos;

  private void jj_add_error_token(int kind, int pos) {
    if (pos >= 100) {
       return;
    }

    if (pos == jj_endpos + 1) {
      jj_lasttokens[jj_endpos++] = kind;
    } else if (jj_endpos != 0) {
      jj_expentry = new int[jj_endpos];

      for (int i = 0; i < jj_endpos; i++) {
        jj_expentry[i] = jj_lasttokens[i];
      }

      for (int[] oldentry : jj_expentries) {
        if (oldentry.length == jj_expentry.length) {
          boolean isMatched = true;

          for (int i = 0; i < jj_expentry.length; i++) {
            if (oldentry[i] != jj_expentry[i]) {
              isMatched = false;
              break;
            }

          }
          if (isMatched) {
            jj_expentries.add(jj_expentry);
            break;
          }
        }
      }

      if (pos != 0) {
        jj_lasttokens[(jj_endpos = pos) - 1] = kind;
      }
    }
  }

  /** Generate ParseException. */
  public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[31];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 15; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 31; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    jj_endpos = 0;
    jj_rescan_token();
    jj_add_error_token(0, 0);
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage, token_source == null ? null : BibTeXParserTokenManager.lexStateNames[token_source.curLexState]);
  }

  /** Enable tracing. */
  final public void enable_tracing() {
  }

  /** Disable tracing. */
  final public void disable_tracing() {
  }

  private void jj_rescan_token() {
    jj_rescan = true;
    for (int i = 0; i < 7; i++) {
      try {
        JJCalls p = jj_2_rtns[i];

        do {
          if (p.gen > jj_gen) {
            jj_la = p.arg; jj_lastpos = jj_scanpos = p.first;
            switch (i) {
              case 0: jj_3_1(); break;
              case 1: jj_3_2(); break;
              case 2: jj_3_3(); break;
              case 3: jj_3_4(); break;
              case 4: jj_3_5(); break;
              case 5: jj_3_6(); break;
              case 6: jj_3_7(); break;
            }
          }
          p = p.next;
        } while (p != null);

        } catch(LookaheadSuccess ls) { }
    }
    jj_rescan = false;
  }

  private void jj_save(int index, int xla) {
    JJCalls p = jj_2_rtns[index];
    while (p.gen > jj_gen) {
      if (p.next == null) { p = p.next = new JJCalls(); break; }
      p = p.next;
    }

    p.gen = jj_gen + xla - jj_la; 
    p.first = token;
    p.arg = xla;
  }

  static final class JJCalls {
    int gen;
    Token first;
    int arg;
    JJCalls next;
  }

}
