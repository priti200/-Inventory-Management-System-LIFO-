# PowerShell helper: compile and run the Swing GUI
Set-Location -Path "C:\Users\SAKETH\Documents\Project\Inventory Management System"
if (-not (Test-Path bin)) { New-Item -ItemType Directory -Path bin | Out-Null }

# Compile main sources and GUI sources
javac -d bin src\main\java\*.java src\gui\java\*.java
if ($LASTEXITCODE -ne 0) { Write-Error "Compilation failed."; exit $LASTEXITCODE }

# Run GUI
java -cp bin InventoryGUI
