
class Polynomial{

   //SUBCLASS
   
   class Term {
      //DATA FIELDS
      int coef;
      int exponent;
      
      //METHODS
      /**constructor 
      @param set_coef coefficient of term
      @param set_exponent exponent of term
      */
      Term(int set_coef, int set_exponent) {
      
         coef = set_coef;
         exponent = set_exponent; 
         
      }
      /**returns a string representation of the object
      @return termString a string representation of Term
      */
      @override
      String toString() {
      
         String termString = exponent > 0 ?
         new String(coef + '^' + exponent) : 
         new String(coef);
         return termString;
         
      }
   }
   
   //DATA FIELDS 
   List<Term> terms = new LinkedList<Term>;
   
   //METHODS 
   /**returns a string representation of a linked list polynomial
   @param polyLinkList a linked list containing Term objects
   @return polyString a string representation of a polynomial
   */
   @override
   String toString(List<Term> polyLinkList){ 
   Iteration<Term> termIteration = polyLinkList.iteration();
   String polyString;
   while(termIteration.hasNext()){
       Term termNext = termIteration.next;
       polyString = polyString + termNext.toString();
   }
   return polyString;
   } 
   /**returns a linked list polynomial
   @param polyString a string representation of a polynomial
   @return polyLinkList a linked listed of term objects
   */
   List readpoly(String polyString){
   List<Term> polyLinkList = new LinkedList<Term>;
   String[] termArray = polyString.split("+");
   for(int i = 0; i < termArray.length(); i++){ 
   String[] termString = termArray[i].split("^");
   Term termObj = new Term(termString[0],termString[1]); 
   polyLinkList.addLast(termObj);    
   }
   return polyLinkList;
   } 
   /**adds two polynomials 
   @param polyA a linked list of terms
   @param polyB a linked list of terms 
   @return polySum a linked list of resulting terms from the summation of polyA and polyB
   */
   List<Term> addpoly(List<Term> polyA, List<Term> polyB){
   
   Iterator<Term> iterateA = new polyA.iterator();
   Iterator<Term> iterateB = new polyB.iterator(); 
   
   while(iterateA.hasNext() || iterateB.hasNext()) { 
      
      int sumCoef, sumExponent; 
      sumExponent = Math.max(iterateA.exponent, iterateB.exponent);
      
      if(iteratorA.exponent == iteratorB.exponent){
      
          sumCoef = iteratorA.coef + iteratorB.coef;
          iteratorA.next();
          iteratorB.next();
          
      }else if(iteratorA.exponent > iteratorB.exponent){
      
          sumCoef = iteratorA.coef;
          iteratorA.next(); 
          
      }else{
      
          sumCoef = iteratorB.coef;
          iteratorB.next();
          
      } 
   }
   
   return List<Term>;
   
   }
   
   /**multiplies one polynomial (linked list of terms) by another
   @param polyA a linked list of Term objects
   @param polyB a linked list of Term objects
   @return productPoly the product of polyA and polyB - a linked list of Term objects 
   */
   List multiplypoly(List<Term> polyA, List<Term> polyB){ 
   
      Iterator<Term> iterateA = new polyA.iterator();
      List<Term> productPoly = new LinkedList<Term>;
      
      while(iterateA.hasNext()) {
      
         Iterator<Term> iterateB = new polyB.iterator(); 
         Term multiplierTerm = iterateA.next();
         List multiplierProductPoly<Term> = new LinkedList<Term>;
         
         while(iteratorB.hasNext()) {
           
            Term currentTerm = iteratorB.next();
            int productCoef = currentTerm.coef * multiplierTerm.corf;
            int productExponent = currentTerm.exponent + multiplierTerm.exponent;	
            Term productTerm = new Term(productCoef, productExponent);
            multiplierProductPoly.addLast(productTerm);
         
         }
         
         productPoly = polyadd(productPoly, multiplierProductPoly);	
      }
      
      return productPoly; 
      
   }
}
