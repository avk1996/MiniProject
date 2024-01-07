using System.Data;
using System.Data.SqlClient;
using System.Net.Http.Headers;
using System.Reflection.PortableExecutable;

namespace MiniProjectStudent
{
    public class Student
    {
        private static int ids=0;
        private int _id;
        private string _name;
        private int _degree;
        private int _deptNo;
        public int Id
        {
            get => Id;
            set
            {
                if (value < 0) 
                    throw new StudentException("ERROR: Invalid Id given");
                else
                {
                    _id = value;
                }
            }
        }
        public Student() 
        {
            Id= ++ids;
        }
        public String? Name
        {
            get => Name;
            set
            {
                if (String.IsNullOrEmpty(value))
                    throw new StudentException("ERROR: Student Name Is Blank!");
                else
                    _name = value;

            }
        }
        public string? Degree { get; set; }
        public int DeptNo { get; set; }
    }
    public class Department
    {
        public int DeptNo { get; set; }
        public string? DeptName { get; set; }
    }
    internal class Program
    {
        static void Main(string[] args)
        {
            bool exit = false;
            while (!exit)
            {
                try
                {
                    Console.WriteLine("Option:\n1. INSERT\n2. UPDATE\n3. DELETE\n4. SELECT\n0. EXIT");
                    switch (int.Parse(Console.ReadLine()!))
                    {
                        case 1:
                            Console.WriteLine("Enter Student Details(Name,Degree,DeptNo)");
                            Student student = new Student
                            { 
                                Name = Console.ReadLine()!,
                                Degree = Console.ReadLine(),
                                DeptNo = int.Parse(Console.ReadLine()!)
                            };
                            InsertStudent(student);
                            break;
                        case 2:
                            break;
                        case 3:
                            Console.WriteLine("Enter Id: ");
                            DeleteStudent(int.Parse(Console.ReadLine()!));
                            break;
                        case 4:
                            Console.WriteLine("Enter Id: ");
                            Student stud = SelectStudent(int.Parse(Console.ReadLine()!));
                            if(stud != null)
                            {
                                Console.WriteLine($"Student details- \nId : {stud.Id} \nName : {stud.Name} \nDegree : {stud.Degree} \nDeptNo : {stud.DeptNo}");
                            }
                            
                            break;
                        case 0:
                            exit = true;
                            Console.WriteLine("bye... :(");
                            break;
                        default:
                            Console.WriteLine("Invalid option...");
                            break;

                    }
                }
                catch (StudentException e)
                {
                    Console.WriteLine(e.Message);
                }
            }
        }

        private static void DeleteStudent(int v)
        {
            Console.WriteLine("delete student api");
            using(SqlConnection sqlConn = new SqlConnection())
            {
                sqlConn.ConnectionString =
                    @"Data Source=(localdb)/ProjectModels;" +
                    "Initial Catalog=Student-DB;" +
                    "Integrated Security=True;" +
                    "Connect Timeout=30;";
                try
                {
                    sqlConn.Open();
                    SqlCommand sqlDelete = new SqlCommand();
                    sqlDelete.Connection = sqlConn;
                    sqlDelete.CommandType = CommandType.StoredProcedure;
                    sqlDelete.CommandText = "DeleteStudent";
                    sqlDelete.Parameters.AddWithValue("@Id", v);
                    sqlDelete.ExecuteNonQuery();
                    Console.WriteLine("Student deleation Success");
                }
                catch(Exception ex)
                {
                    Console.WriteLine("Student deleation Failed");
                    Console.WriteLine($"Reason: {ex.Message}");
                }
            }
        }

        private static void InsertStudent(Student student)
        {
            Console.WriteLine("insert student api");
            using(SqlConnection sqlConn = new SqlConnection())
            {
                    sqlConn.ConnectionString =
                    @"Data Source=(localdb)/ProjectModels;" +
                    "Initial Catalog=Student-DB;" +
                    "Integrated Security=True;" +
                    "Connect Timeout=30;";
                try
                {
                    sqlConn.Open();
                    SqlCommand sqlInsert = new SqlCommand();
                    sqlInsert.Connection = sqlConn;
                    sqlInsert.CommandType = CommandType.StoredProcedure;
                    sqlInsert.CommandText = "InsertStudent";
                    sqlInsert.Parameters.AddWithValue("@Id",student.Id);
                    sqlInsert.Parameters.AddWithValue("@Id",student.Name);
                    sqlInsert.Parameters.AddWithValue("@Id",student.Degree);
                    sqlInsert.Parameters.AddWithValue("@Id",student.DeptNo);
                    sqlInsert.ExecuteNonQuery();

                    Console.WriteLine("Student Insertion Success");
                }
                catch(Exception ex)
                {
                    Console.WriteLine("Student Insertion Failed...");
                }
            }
        }

        private static Student SelectStudent(int id)
        {
            Console.WriteLine("select student api");
            using (SqlConnection sqlConn = new SqlConnection())
            {
                sqlConn.ConnectionString =
                @"Data Source=(localdb)/ProjectModels;" +
                "Initial Catalog=Student-DB;" +
                "Integrated Security=True;" +
                "Connect Timeout=30;";
                try
                {
                    sqlConn.Open();
                    SqlCommand sqlSelect = new SqlCommand();
                    sqlSelect.Connection = sqlConn;
                    sqlSelect.CommandType = CommandType.Text;
                    sqlSelect.CommandText = "SELECT * FROM students WHERE id = @id";
                    sqlSelect.Parameters.AddWithValue("@id", id);
                    using (SqlDataReader reader = sqlSelect.ExecuteReader())
                    {
                        if (reader.Read())
                        {
                            string name = reader.GetString("Name");
                            int ID = reader.GetInt32("Id");
                            string degree = reader.GetString("Degree");
                            int deptNo = reader.GetInt32("DeptNo");

                            return new Student
                            {
                                Id = ID,
                                Name = name,
                                Degree = degree,
                                DeptNo = deptNo
                            };

                        }
                        else
                        {
                            Console.WriteLine($"no records found Id: {id}");
                            return null;
                        }
                    }
                    
                }
                catch (Exception ex)
                {
                    Console.WriteLine("Student Selection Failed...");
                    return null;
                }
            }
        }

    }
    public class StudentException : ApplicationException
    {
        public StudentException(string message) : base(message) { }
    }
}
