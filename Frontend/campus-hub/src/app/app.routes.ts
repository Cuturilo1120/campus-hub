import { Routes } from '@angular/router';
import { Login } from './auth/login/login';
import { Register } from './home/pages/admin/register/register';
import { AdminDashboard } from './home/pages/admin/admin-dashboard/admin-dashboard';
import { UserList } from './home/pages/admin/user-list/user-list';
import { NutritionLayout } from './home/layouts/nutrition-layout/nutrition-layout';
import { NutritionDashboard } from './home/pages/nutrition/nutrition-dashboard/nutrition-dashboard';
import { DormLayout } from './home/layouts/dorm-layout/dorm-layout';
import { DormDashboard } from './home/pages/dorm/dorm-dashboard/dorm-dashboard';
import { AdminLayout } from './home/layouts/admin-layout/admin-layout';
import { UserDetails } from './home/pages/admin/user-details/user-details';

export const routes: Routes = [
    { path: '', component: Login },
    {
        path: 'admin',
        component: AdminLayout,
        children: [
            { path: '', component: AdminDashboard },
            { path: 'users', component: UserList },
            { path: 'register', component: Register },
            { path: 'users/:id', component: UserDetails }
        ]
    },

    {
        path: 'nutrition',
        component: NutritionLayout,
        children: [
            { path: '', component: NutritionDashboard } 
        ]
    },

    {
        path: 'dorm',
        component: DormLayout,
        children: [
            { path: '', component: DormDashboard }
        ]
    }
];
