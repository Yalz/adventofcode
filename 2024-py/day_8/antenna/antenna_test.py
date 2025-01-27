import os

from day_8.antenna.antenna import Antenna
from helper import file_utils


def test_generate_freq_map():
	file_path = os.path.abspath(os.path.join(os.path.dirname(__file__), '../data/example.txt'))
	freq_grid = file_utils.file_to_line_arr(file_path, True)

	antenna = Antenna(freq_grid)
	antenna_ext = Antenna(freq_grid, True)

	assert antenna.anti_node_count == 14
	assert antenna_ext.anti_node_count == 34

def test_generate_freq_map_ext():
	file_path = os.path.abspath(os.path.join(os.path.dirname(__file__), '../data/example-pt2.txt'))
	freq_grid = file_utils.file_to_line_arr(file_path, True)

	antenna_ext = Antenna(freq_grid, True)

	assert antenna_ext.anti_node_count == 9

def test_input():
	file_path = os.path.abspath(os.path.join(os.path.dirname(__file__), '../data/input.txt'))
	freq_grid = file_utils.file_to_line_arr(file_path, True)

	antenna = Antenna(freq_grid)

	print("Part 1", antenna.anti_node_count)
	assert antenna.anti_node_count == 303

	antenna = Antenna(freq_grid, True)

	print("Part 2", antenna.anti_node_count)
	assert antenna.anti_node_count == 303