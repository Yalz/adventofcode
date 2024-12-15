import re


class Report:
    def __init__(self, file_location, tolerate_bad_level=False):
        self.report_values = []
        self.tolerate_bad_level = tolerate_bad_level
        file = open(file_location, "r")

        while True:
            content = file.readline()
            if not content:
                break
            # Extract integers using regular expressions
            string_numbers = re.findall(r'\d+', content)

            # Convert the extracted strings to integers
            self.report_values.append([int(num) for num in string_numbers])

        file.close()

    def overview(self):
        print(self.report_values)

    def validate_report(self, debug):
        valid_reports = 0
        for v in self.report_values:
            report_valid = validate_level(v, debug, self.tolerate_bad_level)
            if report_valid:
                valid_reports += 1
            if debug:
                print(report_valid, v)
        return valid_reports


def validate_level(row: list[int], debug: bool, tolerate_bad_level: bool):
    previous_diff = None

    for idx, v in enumerate(row):
        if idx > 0:
            diff = v - row[idx - 1]
            if abs(diff) < 1 or abs(diff) > 3:
                if debug:
                    print(f"Prev val {row[idx - 1]} differs {diff} from {v}")
                if idx > 1 and tolerate_bad_level:
                    diff = v - row[idx - 2]
                    if abs(diff) < 1 or abs(diff) > 3:
                        if debug:
                            print("EXTRA LEVEL: diff for value ", v, " was ", diff)
                        return False
                else:
                    return False

            if previous_diff is not None:
                if (diff < 0 < previous_diff) or (diff > 0 > previous_diff):
                    if debug:
                        print("diff for value ", v, " was ", diff, " and previous diff was ", previous_diff)

                    if idx > 1 and tolerate_bad_level:
                        diff = v - row[idx - 2]
                        if (diff < 0 < previous_diff) or (diff > 0 > previous_diff):
                            return False
                    else:
                        return False
            previous_diff = diff
    return True
