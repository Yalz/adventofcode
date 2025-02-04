def file_to_line_arr(file_path, as_2d=False):
	file = open(file_path, "r")
	grid = []
	while True:
		content = file.readline()
		if not content:
			break
		content = content.replace("\n", "")
		if as_2d:
			grid.append(list(content))
		else:
			grid.append(content)

	return grid

def file_to_str(file_path):
	file = open(file_path, "r")
	content = file.read()
	return content