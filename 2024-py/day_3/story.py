from calculator.calculator import *

with open("./data/input.txt", 'r') as file:
    file_content = file.read()

pt_1 = process_commands(file_content)
pt_2 = process_commands(file_content, True)
print(f"Part 1: {pt_1}, Part 2: {pt_2}")