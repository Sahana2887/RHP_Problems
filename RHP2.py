a = input("Enter a string: ")
letters = {ch for ch in a if 'A' <= ch <= 'Z'}
if len(letters) == 26:
    print("The string is a pangram.")  
else:
    print("The string is not a pangram.")