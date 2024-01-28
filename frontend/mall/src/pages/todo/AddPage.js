import AddComponent from "../../components/todo/AddComponent";

const AddPage = () => {

    return (
        <div className="p-4 w-full bg-white">
            <div className="text-3xl font-extrabold">
                Todo Add Page
            </div>
            {/* 모달 창 컴포넌트 붙이기 */}
            <AddComponent/> 
        </div>
    );
}

export default AddPage;