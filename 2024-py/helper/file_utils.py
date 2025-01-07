def file_to_line_arr(file_path):
	file = open(file_path, "r")
	grid = []
	while True:
		content = file.readline()
		if not content:
			break
		grid.append(content.replace("\n", ""))

	return grid