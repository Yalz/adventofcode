from day_9.disk_fragmenter.disk_fragmenter import DiskFormatter


def test_disk_map_simple():
	df = DiskFormatter('12345')
	assert df.print() == '0..111....22222'
	df.defrag()
	assert df.print() == '022111222......'


def test_disk_map():
	df = DiskFormatter('2333133121414131402')
	assert df.print() == '00...111...2...333.44.5555.6666.777.888899'
	df.defrag()
	assert df.print() == '0099811188827773336446555566..............'
	assert df.calculate_checksum() == 1928


def test_input():
	input_text = open('../data/input.txt', "r").readline().replace("\n", "")
	df = DiskFormatter(input_text)
	print(df.print())
	df.defrag()
	print(df.print())
	assert df.calculate_checksum() > 90179689804
	print("Part 1", df.calculate_checksum())
