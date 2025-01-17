import os

from day_8.antenna.antenna import Antenna
from helper import file_utils


def test_generate_freq_map():
	file_path = os.path.abspath(os.path.join(os.path.dirname(__file__), '../data/example.txt'))
	freq_grid = file_utils.file_to_line_arr(file_path, True)

	antenna = Antenna(freq_grid)

	assert antenna.anti_node_count == 14

def test_input():
	file_path = os.path.abspath(os.path.join(os.path.dirname(__file__), '../data/input.txt'))
	freq_grid = file_utils.file_to_line_arr(file_path, True)

	antenna = Antenna(freq_grid)

	print("Part 1", antenna.anti_node_count)
	assert antenna.anti_node_count == 303