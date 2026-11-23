
import math


def sss(salary):
    # Monthly Salary Credit: nearest 500, minimum 5,000, maximum 35,000
    msc = min(max(math.floor(salary / 500 + 0.5) * 500, 5000), 35000)
    return msc * 0.05


def philhealth(salary):
    # 5% premium, shared equally; salary floor 10,000, ceiling 100,000
    return min(max(salary, 10000), 100000) * 0.025


def pagibig(salary):
    # 2% of salary, salary capped at 10,000 (max 200)
    return min(salary, 10000) * 0.02


def income_tax(taxable):
    # BIR monthly withholding tax (TRAIN law)
    if taxable <= 20833:
        return 0
    elif taxable <= 33333:
        return (taxable - 20833) * 0.15
    elif taxable <= 66667:
        return 1875 + (taxable - 33333) * 0.20
    elif taxable <= 166667:
        return 8541.80 + (taxable - 66667) * 0.25
    elif taxable <= 666667:
        return 33541.80 + (taxable - 166667) * 0.30
    return 183541.80 + (taxable - 666667) * 0.35


def display_report(name, salary):
    s, p, h = sss(salary), philhealth(salary), pagibig(salary)
    taxable = salary - (s + p + h)
    tax = income_tax(taxable)
    total = s + p + h + tax
    print(f"Employee Name: {name}")
    print(f"Monthly Salary: {salary:,.2f}")
    print(f"SSS: {s:,.2f}")
    print(f"PhilHealth: {p:,.2f}")
    print(f"Pag-IBIG: {h:,.2f}")
    print(f"Taxable Income: {taxable:,.2f}")
    print(f"Withholding Tax: {tax:,.2f}")
    print(f"Total Deductions: {total:,.2f}")
    print(f"Net Pay: {salary - total:,.2f}")


def main():
    count = int(input("Number of employees: "))
    for i in range(count):
        print()
        name = input("Employee Name: ")
        salary = float(input("Monthly Salary: "))
        print()
        display_report(name, salary)


if __name__ == "__main__":
    main()
