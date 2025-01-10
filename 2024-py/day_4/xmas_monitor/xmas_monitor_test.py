import os

import numpy as np

from helper.file_utils import file_to_line_arr
from ..xmas_monitor.xmas_monitor import *


def test_find_word():
	file_path = os.path.abspath(os.path.join(os.path.dirname(__file__), '../data/example.txt'))
	grid = file_to_line_arr(file_path)

	print()
	result = find_xmas(parse_grid(grid))

	assert 18 == result


def test_find_word_simplified():
	file_path = os.path.abspath(os.path.join(os.path.dirname(__file__), '../data/example-simplified.txt'))
	grid = file_to_line_arr(file_path)

	print()
	result = find_xmas(parse_grid(grid))

	assert 18 == result

def test_input():
	file_path = os.path.abspath(os.path.join(os.path.dirname(__file__), '../data/input.txt'))
	grid = file_to_line_arr(file_path)

	print()
	print("Part 1 ", find_xmas(parse_grid(grid)))
	print("Part 2 ", find_mas_x(parse_grid(grid)))

def test_diagonals_of():
	grid = [
		"123",
		"456",
		"789",
	]

	expected_diags = [['1'], ['4', '2'], ['7', '5', '3'], ['8', '6'], ['9'], ['3'], ['2', '6'], ['1', '5', '9'], ['4', '8'], ['7']]

	print()
	assert expected_diags == diagonals_of(parse_grid(grid))

def test_find_mas_x():
	file_path = os.path.abspath(os.path.join(os.path.dirname(__file__), '../data/example-pt2-simplified.txt'))
	grid = parse_grid(file_to_line_arr(file_path))

	print()

	assert 9 == find_mas_x(grid)
	file_path = os.path.abspath(os.path.join(os.path.dirname(__file__), '../data/td-1.txt'))
	grid = parse_grid(file_to_line_arr(file_path))

	assert 2 == find_mas_x(grid)