import os

from day_7.stolen_operators.stolen_operators import validate_operation_string, validate_operations
from helper.file_utils import file_to_line_arr


def test_validate_single_operation():
	assert validate_operation_string("190: 10 19", ['+', '*']) == 190
	assert validate_operation_string("3267: 81 40 27", ['+', '*']) == 3267
	assert validate_operation_string("292: 11 6 16 20", ['+', '*']) == 292
	assert validate_operation_string("3456: 10 10 4 8 128 128", ['+', '*']) == 3456
	assert validate_operation_string("9608: 5 4 9 20 40 6 2 4", ['+', '*']) == 9608


def test_validate_single_operation_concat_ext():
	assert validate_operation_string("156: 15 6", ['+', '*', '||']) == 156
	assert validate_operation_string("7290: 6 8 6 15", ['+', '*', '||']) == 7290
	assert validate_operation_string("192: 17 8 14", ['+', '*', '||']) == 192


def test_validate_operations():
	file_path = os.path.abspath(os.path.join(os.path.dirname(__file__), '../data/example.txt'))
	operations = file_to_line_arr(file_path)

	assert validate_operations(operations, ['+', '*']) == 3749
	assert validate_operations(operations, ['+', '*', '||']) == 11387


def test_validate_operations_td():
	file_path = os.path.abspath(os.path.join(os.path.dirname(__file__), '../data/td-1.txt'))
	operations = file_to_line_arr(file_path)

	assert validate_operations(operations, ['+', '*']) == 672


def test_validate_operations_input():
	file_path = os.path.abspath(os.path.join(os.path.dirname(__file__), '../data/input.txt'))
	operations = file_to_line_arr(file_path)

	print()
	sol_pt1 = validate_operations(operations, ['+', '*'])
	assert sol_pt1 > 465126101684
	assert sol_pt1 < 465139525520
	print("Part 1:", sol_pt1)
	sol_pt2 = validate_operations(operations, ['+', '*', '||'])
	print("Part 2:", sol_pt2)