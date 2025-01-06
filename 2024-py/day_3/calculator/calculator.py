import re


def parse_valid_commands(line):
    regex = r"(mul)\((\d+),(\d+)\)"
    return re.findall(regex, line)


def parse_conditionals(line) -> list[str]:
    regex_ignore = r"don't\(\)(?:[\s\S]*?)do\(\)"
    return re.split(regex_ignore, line)

def filter_instructions(line:str) -> str:
    regex_trailing = r"don't\(\)(?:[\s\S]*?)"
    filtered_instructions = "".join(parse_conditionals(line))
    return re.split(regex_trailing, filtered_instructions)[0]

def process_commands(line: str, filter_conditionals=False):
    product = 0

    if filter_conditionals:
        line = filter_instructions(line)

    for mul in parse_valid_commands(line):
        product += (int(mul[1]) * int(mul[2]))

    return product
