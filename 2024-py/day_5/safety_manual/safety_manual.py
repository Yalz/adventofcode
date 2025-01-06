import re

def extract_data(filepath):
	file = open(filepath, "r")

	prio_val = {}
	instructions = []

	while True:
		content = file.readline()
		if not content:
			break
		if content == "\n":
			break
		string_numbers = re.findall(r'\d+', content)

		if string_numbers[0] not in prio_val:
			prio_val[string_numbers[0]] = []
		prio_val[string_numbers[0]].append(string_numbers[1])

	while True:
		content = file.readline()
		if not content:
			break
		# Extract integers using regular expressions
		string_numbers = re.findall(r'\d+', content)

		# Convert the extracted strings to integers
		instructions.append(string_numbers)
	file.close()

	return prio_val, instructions


def reorder_instruction(prio, instruction):
	common_elements = {}
	for i in list(map(str, instruction)):
		common_elements[i] = list(set(prio.get(i, [])) & set(list(map(str, instruction))))

	return sorted(common_elements.keys(), key=lambda k: len(common_elements[k]), reverse=True)


def validate(prio, instructions, reorder=False):
	valid_sum = 0
	for instruction in instructions:
		report_valid = validate_instruction(prio, instruction)
		if report_valid and reorder == False:
			valid_sum += int(instruction[int((len(instruction) - 1)/2)])
		elif report_valid == False and reorder:
			instruction = reorder_instruction(prio, instruction)
			valid_sum += int(instruction[int((len(instruction) - 1) / 2)])
	return valid_sum

def validate_instruction(prio, instruction):
	for i in range(len(instruction)):
		if instruction[i] in prio:
			for prio_val in prio.get(instruction[i]):
				if prio_val in instruction and instruction.index(prio_val) < i:
					return False
	return True