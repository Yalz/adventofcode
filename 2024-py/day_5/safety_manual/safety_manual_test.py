import os

from .safety_manual import extract_data, validate, reorder_instruction


def test_basic():
	file_path = os.path.abspath(os.path.join(os.path.dirname(__file__), '../data/example.txt'))
	prio, instructions = extract_data(file_path)

	assert 143 == validate(prio, instructions)
	assert 123 == validate(prio, instructions, True)

def test_reorder():
	file_path = os.path.abspath(os.path.join(os.path.dirname(__file__), '../data/example.txt'))
	prio, instructions = extract_data(file_path)

	assert ['97','75','47','61','53'] == reorder_instruction(prio, [75,97,47,61,53])
	assert ['61','29','13'] == reorder_instruction(prio, [61,13,29])
	assert ['97','75','47','29','13'] == reorder_instruction(prio, [97,13,75,29,47])

def test_input():
	file_path = os.path.abspath(os.path.join(os.path.dirname(__file__), '../data/input.txt'))
	prio, instructions = extract_data(file_path)

	print("Phase 1", validate(prio, instructions))
	print("Phase 2", validate(prio, instructions, True))