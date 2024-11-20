let activeRole = 'user'; // Store the current active role

        // Show and hide account form or dropdown
        document.getElementById('account-btn').onclick = (e) => {
            const dropdownMenu = document.getElementById("dropdown-menu");
            if (dropdownMenu.style.display === "block") {
                dropdownMenu.style.display = "none";
            } else {
                dropdownMenu.style.display = "block";
            }
        };

        document.getElementById('close-form').onclick = () => {
            document.querySelector('.account-form').classList.remove('active');
        };

        // Show appropriate form based on selected role
        const roleOptions = document.querySelectorAll('.role-option');
        roleOptions.forEach(option => {
            option.addEventListener('click', function () {
                const role = this.getAttribute('data-role');
                activeRole = role; // Set the active role
                showForm(role);
                document.querySelector('.account-form').classList.add('active'); // Show form
                document.getElementById("dropdown-menu").style.display = 'none'; // Hide dropdown after selection
            });
        });

        function showForm(role) {
            // Hide all login forms
            document.querySelectorAll('.account-form form').forEach(form => form.classList.remove('active'));
            // Show the login form based on role
            if (role === 'user') {
                document.querySelector('.login-form').classList.add('active');
            } else if (role === 'admin') {
                document.querySelector('.admin-form').classList.add('active');
            } else if (role === 'trainee') {
                document.querySelector('.trainee-form').classList.add('active');
            }
        }

        // Toggle login and registration forms
        document.querySelector('.login-btn').onclick = () => {
            // Show the login form for the active role
            showForm(activeRole);
        };
        
        document.querySelector('.register-btn').onclick = () => {
            
            document.querySelectorAll('.account-form form').forEach(form => form.classList.remove('active'));

            document.querySelector('.registration-form').classList.add('active');
        };

        document.querySelector('.load-more .btn').onClick =()=>{
            document.querySelectorAll('.courses .box-container .hide').forEach(show =>{
              show.style.display = 'block';
            });
            document.querySelector('.load-more .btn').style.display ='none';  
        };
