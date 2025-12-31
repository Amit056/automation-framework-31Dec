Step 1: Initialize Git repository
Command: git init
Explanation: Creates a local Git repository by generating a .git folder.

Step 2: Add files to staging area
Command: git add .
Explanation: Stages all project files so they are ready to be committed.

Step 3: Commit changes (corrected command)
Command: git commit -m "Initial commit"
Explanation: Saves the staged files into Git history with a commit message. The -m flag is mandatory.

Step 4: Create repository on GitHub
Action:
Create a new repository on GitHub (do not initialize with README).
Example URL:
https://github.com/Amit056/automation-framework-31Dec.git

Explanation: This will be your remote repository.

Step 5: Add GitHub remote repository
Command: git remote add origin https://github.com/Amit056/automation-framework-31Dec.git

Explanation: Links your local repository to the GitHub repository named origin.

Step 6: Verify remote configuration
Command: git remote -v
Explanation: Confirms the remote repository URLs for fetch and push.

Step 7: Rename branch to main
Command: git branch -M main
Explanation: Renames the current branch (usually master) to main.

Step 8: Push code to GitHub
Command: git push -u origin main
Explanation: Pushes the local main branch to GitHub and sets upstream tracking so future pushes can use git push.

Result:
Your local source code is now successfully pushed to GitHub, and the main branch is properly linked to the remote repository.