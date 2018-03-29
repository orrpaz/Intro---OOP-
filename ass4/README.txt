advanced simplification:
in order to achieve these function successfully I have used instaceof in some of the cases.
i suppurt on this function for example:

cos(-x) = cos(x) :
if the inner expression is negative, we create an new Cos and the new inner expression is Neg of the inner expression according to List of trigonometric identities

sin(-x) = -sin(x) :
if the inner expression is negative - return a new Neg of the sine, and the inner expression of the sine is also negative according to List of trigonometric identities.

 0/x = 0 :
if the Numerator is 0 - return a new number 0.

x/-x = -1 
-x/x = -1 :
if the Numerator is negative of the denominator, or the denominator is negative of the numerator - return  -1.

 log(y,(x^2)) = 2log(y,x) :
if the exponant is a pow - return new Mult of 2 and new log of y and the base of the power according to Logarithm rules.

 x-(-x) = 2x :
if the second expression is negative of the first expression and there is a minus between them
- return an adding of 2 variable.

 x+(-x) = 0 or (-x)+x = 0 :
if the first expression is negative of the second expression, or the second expression is negative of the first expression and there is a plus between them
- return a number 0;

x*x = x^2 :
if the two expressions are identical - return new Pow of 2.

 x*(y/x) = y :
if the second expression is division and the denominator is equal to the first expression - return the numerator.

-(-x) = x :
if the inner expression is Neg - return the negative of the inner expression.

 x+x = 2x :
if the two expressions are identical - return new Plus of the first expression and 2.

 x*x = x^2 :
if the two expressions are identical - return new Pow of the first expression and 2.

 (x^y)^z = x^(y*z) 
 ((((x^y)^y)^y)^y) = (x^(y^4) :
if the first expression is power - return a new Pow of the first variable of the first expression and new Mult of the second variable of the first expression, and the second variable of the power.
also i suppurt on another combination of pow and mult simplification.

cos(x+y)\cos(y+x) = 1:
i also support with comatative expression like the example above
if the first expression is comatative to the first expression  - it is return 1.
