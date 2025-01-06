from day_2.report.report import Report

report = Report("data/input.txt")

print(Report("data/input.txt").validate_report())
print(Report("data/input.txt", True).validate_report())