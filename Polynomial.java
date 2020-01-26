import java.util.*;

class Polynomial{

	//SUBCLASS

	public class Term {
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

		/**returns the coeffiecent*/
		public int getCoef(){
			return coef;
		}

		/**returns the exponent*/
		public int getExponent(){
			return exponent;
		}

		/**returns a string representation of the object
		@return termString a string representation of Term
		*/
		@Override
		public String toString() {
			String termString = (exponent > 1) ? new String(coef + "^" + exponent) : new String(String.valueOf(coef));
			return termString;
		}

	}

	//DATA FIELDS
	LinkedList<Term> terms = new LinkedList<Term>();

	//METHODS
	/**constructor passes a string representation of a polynomial and parses the string into a LinkedList of Term object(s)
	@param set_poly sets the contents of the polynomial with a string
	*/
	Polynomial(String set_poly){
		terms = readpoly(set_poly);
	}

	/**returns a string representation of a linked list polynomial
	@param polyLinkList a linked list containing Term objects
	@return polyString a string representation of a polynomial
	*/
	@Override
	public String toString() {
		Iterator<Term> termIterator = terms.iterator();
		String polyString = new String();

		while(termIterator.hasNext()){
			Term termNext = termIterator.next();
			polyString = polyString + termNext.toString();
			polyString = termIterator.hasNext() ? polyString + "+" : polyString + "";
		}

		return polyString;
	}

	/**returns a linked list polynomial
	@param polyString a string representation of a polynomial
	@return polyLinkList a linked listed of term objects
	*/
	LinkedList<Term> readpoly(String polyString){
		LinkedList<Term> polyLinkList = new LinkedList<Term>();
		String[] termArray = polyString.split("\\+");

		for(String singleTerm : termArray){

			String[] termString = singleTerm.split("\\^");
			try {
				Term termObj = new Term(Integer.parseInt(termString[0]),Integer.parseInt(termString[1]));
				polyLinkList.addLast(termObj);
			} catch(ArrayIndexOutOfBoundsException e) {
				Term termObj = new Term(Integer.parseInt(termString[0]), 1);
				polyLinkList.addLast(termObj);
			}

		}

		return polyLinkList;
	}

	/**adds two polynomials
	@param polyA a linked list of terms
	@param polyB a linked list of terms
	@return polySum a linked list of resulting terms from the summation of polyA and polyB
	*/
	public LinkedList<Term> addpoly(Polynomial aPoly){
		LinkedList<Term> sumPoly = new LinkedList<Term>();
		LinkedList<Term> aPoly_LL = readpoly(aPoly.toString());
		Term voidTerm = new Term(0,0);

		Iterator<Term> iterateA = terms.iterator();
		Iterator<Term> iterateB = aPoly_LL.iterator();

		boolean alignmentHold_TermA = false;
		boolean alignmentHold_TermB = false;

		while(iterateA.hasNext() || iterateB.hasNext()) {
			if(iterateA.hasNext())
				Term termA = alignmentHold_TermA ? voidTerm : iterateA.next();
			if(iterateB.hasNext())
				Term termB = alignmentHold_TermB ? voidTerm : iterateB.next();

			int exponentA = termA.getExponent();
			int exponentB = termB.getExponent();

			int sumCoef = termA.getCoef() + termB.getCoef();
			int sumExponent = Math.max(exponentA, exponentB);

			Term sumTerm = new Term(sumCoef, sumExponent);
			sumPoly.addLast(sumTerm);

			alignmentHold_TermA = exponentA < exponentB ? true : false;
			alignmentHold_TermB = exponentB < exponentA ? true : false;
		}

		return sumPoly;
	}

	/**multiplies one polynomial (linked list of terms) by another
	@param polyA a linked list of Term objects
	@param polyB a linked list of Term objects
	@return productPoly the product of polyA and polyB - a linked list of Term objects
	*/
	public List multiplypoly(Polynomial mPoly){
		List<Term> productPoly = new LinkedList<Term>();
		Polynomial productObject = new Polynomial("");
		String productString = "";
		Iterator<Term> iterateA = terms.iterator();

		while(iterateA.hasNext()) {
			Term multiplierTerm = iterateA.next();
			LinkedList<Term> mPoly_LL = readpoly(mPoly.toString());
			Iterator<Term> iterateB = mPoly_LL.iterator();

			while(iterateB.hasNext()) {
				Term currentTerm = iterateB.next();

				int productCoef = currentTerm.getCoef() * multiplierTerm.getCoef();
				int productExponent = currentTerm.getExponent() + multiplierTerm.getExponent();

				Term productTerm = new Term(productCoef, productExponent);

				productString = productString + productTerm.toString();
			}

			Polynomial tempPoly = new Polynomial(productString);
			productPoly = addpoly(tempPoly);
		}

		return productPoly;
	}
}
