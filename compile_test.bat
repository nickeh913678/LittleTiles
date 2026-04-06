@echo off
cd /d f:\LT3
set JAVA_TOOL_OPTIONS=-Dnet.minecraftforge.gradle.check.certs=false
.\gradlew.bat compileJava --offline --console=plain > compile_result.log 2>&1
echo.
echo === Compilation Result ===
if %ERRORLEVEL% EQU 0 (
    echo COMPILATION SUCCESSFUL ✓
) else (
    echo COMPILATION FAILED ✗
    echo ErrorLevel: %ERRORLEVEL%
)
echo.
echo === Last 50 lines of log ===
for /f "skip=40" %%a in (compile_result.log) do echo %%a
