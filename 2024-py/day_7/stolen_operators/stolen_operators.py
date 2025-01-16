import itertools

# Function to generate unique combinations for n characters
def generate_combinations(array, n):
	# Generate combinations with repetition of the array with length n
	combinations = list(itertools.product(array, repeat=n))

	return [list(item) for item in combinations]

def validate_operations(input_str: [str], operators) -> int:
	result = 0
	for operation in input_str:
		result += validate_operation_string(operation, operators)

	return result

def validate_operation_string(input_str: str, operators) -> int:
	result = int(input_str.split(":")[0])
	values = list(map(int, input_str.split(":")[1].strip().split(" ")))
	return validate_operation(result, values, operators)

def calculate_operation(operations: [str]) -> int:
	val = int(operations[0])

	for oIdx in range(0, len(operations)-1):
		if operations[oIdx].isnumeric():
			continue
		if operations[oIdx] == '+':
			val += int(operations[oIdx+1])
		elif operations[oIdx] == '*':
			val *= int(operations[oIdx+1])
		elif operations[oIdx] == '||':
			val = int(f'{val}{operations[oIdx+1]}')

	return val



def validate_operation(expected_result, values: [int], operators) -> int:

	patterns = generate_combinations(operators, len(values)-1)

	return_val = 0

	for pattern in patterns:
		result = values[0]
		full_operation = [str(values[0])]
		for i in range(0, len(values)-1):
			full_operation.append(pattern[i])
			match pattern[i]:
				case '+':
					result += values[i+1]
					full_operation.append(str(values[i + 1]))
				case '*':
					result *= values[i+1]
					full_operation.append(str(values[i + 1]))
				case '||':
					result = values[i+1]
					full_operation.append(str(result))

		combination_result = calculate_operation(full_operation)
		if combination_result == expected_result:
			return expected_result

	return return_val