# Sodoko_AI
This is a sodoko project for AmirKabir University in Tehran based on java programming language
the format of the entries are like this example :
5 3
r g b y p
1# *b *#
*# 3r *#
*g 1# *#

which the first number is the number of colors which are declared in the line below and the next number is the number of columns and rows. because the matrix is in square shape.
in this kind of sodoko game our program solves it with backtracking algorithm and MRV and Degree heuristics. in each house we have a string consists of a number and a character which the number is the number in the house and the character is the color of the house
the rules are that we mustn't have the repetitive number in each row and column and the color of the side houses must be different
and the color of the house with higher number should have the color with the higher priority 
