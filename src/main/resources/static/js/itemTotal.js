
document.addEventListener('DOMContentLoaded', () => {

  const filterButton = document.querySelector('.filter');

  const filterBox = document.querySelector('.popUpFilterBox_wrapper');

  const filterButtonArrow = document.querySelector('.filter > img');

  filterButton.addEventListener('click', () => {

    if (filterBox.style.display === 'block') {
      filterBox.style.display = 'none';
      filterButton.style.border = '1px solid rgb(244, 244, 244)';
      filterButtonArrow.style.transform = 'rotate(0deg)';
    } else {
      filterBox.style.display = 'block';
      filterButton.style.border = '1px solid rgb(81, 151, 242)';
      filterButtonArrow.style.transform = 'rotate(180deg)';
    }

  });
});


