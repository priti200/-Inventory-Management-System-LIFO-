# PowerShell helper: compile sources and run the programmatic tests
Set-Location -Path "C:\Users\SAKETH\Documents\Project\Inventory Management System"
if (-not (Test-Path bin)) { New-Item -ItemType Directory -Path bin | Out-Null }

# Compile main sources and tests
javac -d bin src\main\java\*.java src\test\java\*.java
if ($LASTEXITCODE -ne 0) { Write-Error "Compilation failed."; exit $LASTEXITCODE }

# Run programmatic tests (InventoryTests)
java -cp bin InventoryTests

# To run the interactive CLI with the sample input, you can run:
# Get-Content test_input.txt -Raw | java -cp bin Main
