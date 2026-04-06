$ErrorActionPreference = "Stop"
cd "f:\LT3"
try {
    git show e23a3f045 | Out-File -Encoding UTF8 -FilePath "diff_e23a3f045.txt" -ErrorAction Stop
    Write-Host "First diff captured"
} catch {
    Write-Host "Error: $_"
}
try {
    git show b7c0f667e | Out-File -Encoding UTF8 -FilePath "diff_b7c0f667e.txt" -ErrorAction Stop
    Write-Host "Second diff captured"
} catch {
    Write-Host "Error: $_"
}
