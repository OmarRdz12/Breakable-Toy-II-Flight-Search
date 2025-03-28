import SearchPanel from "./components/SearchPanel"
import GlobalContextProvider from "./context/GlobalContext"

function App() {
  return (
    <GlobalContextProvider>
      <div className="w-screen h-screen flex flex-col items-center p-4 gap-4">
        <SearchPanel />
      </div>
    </GlobalContextProvider>
  )
}

export default App
