import { Routes } from '@angular/router';
import { Login } from './auth/login/login';
import { Register } from './home/pages/admin/register-user/register';
import { AdminDashboard } from './home/pages/admin/admin-dashboard/admin-dashboard';
import { UserList } from './home/pages/admin/user-list/user-list';
import { NutritionLayout } from './home/layouts/nutrition-layout/nutrition-layout';
import { NutritionDashboard } from './home/pages/nutrition/nutrition-dashboard/nutrition-dashboard';
import { DormLayout } from './home/layouts/dorm-layout/dorm-layout';
import { DormDashboard } from './home/pages/dorm/dorm-dashboard/dorm-dashboard';
import { AdminLayout } from './home/layouts/admin-layout/admin-layout';
import { UserDetails } from './home/pages/admin/user-details/user-details';
import { RegisterStudent } from './home/pages/register-student/register-student';
import { StudentDetails } from './home/pages/student-details/student-details';
import { StudentList } from './home/pages/student-list/student-list';
import { StudentLayout } from './home/layouts/student-layout/student-layout';
import { DormList } from './home/pages/dorm/dorm-list/dorm-list';
import { DormDetails } from './home/pages/dorm/dorm-details/dorm-details';
import { PavilionList } from './home/pages/dorm/pavilion-list/pavilion-list';
import { PavilionDetails } from './home/pages/dorm/pavilion-details/pavilion-details';
import { RoomList } from './home/pages/dorm/room-list/room-list';
import { RoomDetails } from './home/pages/dorm/room-details/room-details';
import { DormCreate } from './home/pages/dorm/dorm-create/dorm-create';
import { PavilionCreate } from './home/pages/dorm/pavilion-create/pavilion-create';
import { RoomCreate } from './home/pages/dorm/room-create/room-create';
import { RoomApplicationList } from './home/pages/dorm/room-application-list/room-application-list';
import { RoomApplicationDetails } from './home/pages/dorm/room-application-details/room-application-details';
import { DormStayList } from './home/pages/dorm/dorm-stay-list/dorm-stay-list';
import { DormStayDetails } from './home/pages/dorm/dorm-stay-details/dorm-stay-details';
import { StudentDashboard } from './home/pages/student/student-dashboard/student-dashboard';
import { MyCard } from './home/pages/student/my-card/my-card';
import { StudentMenu } from './home/pages/student/student-menu/student-menu';
import { CookMenu } from './home/pages/nutrition/cook-menu/cook-menu';
import { ConsumeMeal } from './home/pages/nutrition/consume-meal/consume-meal';
import { MyRoomApplications } from './home/pages/student/my-room-applications/my-room-applications';
import { RoomApplicationApply } from './home/pages/student/room-application-apply/room-application-apply';

export const routes: Routes = [
  { path: '', component: Login },
  {
    path: 'admin',
    component: AdminLayout,
    children: [
      { path: '', component: AdminDashboard },
      { path: 'users', component: UserList },
      { path: 'register', component: Register },
      { path: 'users/:id', component: UserDetails },
    ],
  },

  {
    path: 'nutrition',
    component: NutritionLayout,
    children: [
      { path: '', component: NutritionDashboard },
      { path: 'register-student', component: RegisterStudent },
      { path: 'students', component: StudentList },
      { path: 'students/:id', component: StudentDetails },
      { path: 'menu', component: CookMenu },
      { path: 'consume-meal', component: ConsumeMeal },
    ],
  },

  {
    path: 'dorm',
    component: DormLayout,
    children: [
      { path: '', component: DormDashboard },
      { path: 'register-student', component: RegisterStudent },
      { path: 'students', component: StudentList },
      { path: 'students/:id', component: StudentDetails },
      { path: 'dorms', component: DormList },
      { path: 'dorms/create', component: DormCreate },
      { path: 'dorms/:id', component: DormDetails },
      { path: 'pavilions', component: PavilionList },
      { path: 'pavilions/create', component: PavilionCreate },
      { path: 'pavilions/:id', component: PavilionDetails },
      { path: 'rooms', component: RoomList },
      { path: 'rooms/create', component: RoomCreate },
      { path: 'rooms/:id', component: RoomDetails },
      { path: 'room-applications', component: RoomApplicationList },
      { path: 'room-applications/:id', component: RoomApplicationDetails },
      { path: 'dorm-stays', component: DormStayList },
      { path: 'dorm-stays/:id', component: DormStayDetails },
    ],
  },

  {
    path: 'student',
    component: StudentLayout,
    children: [
      { path: '', component: StudentDashboard },
      { path: 'my-card', component: MyCard },
      { path: 'menu', component: StudentMenu },
      { path: 'room-applications', component: MyRoomApplications },
      { path: 'room-applications/apply', component: RoomApplicationApply },
    ],
  },
];
